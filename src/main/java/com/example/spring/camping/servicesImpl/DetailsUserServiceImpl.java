package com.example.spring.camping.servicesImpl;

import com.example.spring.camping.models.ManageUsers.DetailsUser;
import com.example.spring.camping.models.ManageUsers.Role;
import com.example.spring.camping.models.ManageUsers.User;
import com.example.spring.camping.models.TypeProduct;
import com.example.spring.camping.models.boutique.CategoriesProduct;
import com.example.spring.camping.models.boutique.Commande;
import com.example.spring.camping.models.boutique.Product;
import com.example.spring.camping.respositories.DetailsUserRepository;
import com.example.spring.camping.respositories.UserRepository;
import com.example.spring.camping.respositories.boutique.CommandeRepository;
import com.example.spring.camping.respositories.boutique.ProductRepository;
import com.example.spring.camping.services.ICrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DetailsUserServiceImpl implements ICrud<DetailsUser> {

    @Autowired
    private DetailsUserRepository detailsUserRepository;
    @Autowired
    private UserRepository utilRepo ;

    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List <DetailsUser> getAll() {
        return detailsUserRepository.findAll();
    }

    @Override
    public DetailsUser getById(long id) {
        return detailsUserRepository.getById(id);
    }

    @Override
    public DetailsUser add(DetailsUser detailsUser) {
        return null;
    }


    public DetailsUser addDetailUser(DetailsUser detailsUser, String email) {
        Optional<User> u = utilRepo.findByEmail(email);

        if (u.isPresent()) {
            User user = u.get();
            detailsUser.setStatus(true);
            detailsUser.setUser(user); // Assurez-vous de définir l'utilisateur pour le détail utilisateur
            detailsUserRepository.save(detailsUser);

            // Vérifiez si la liste est null, si oui, initialisez-la
            if (user.getDetailsUser() == null) {
                user.setDetailsUser(new ArrayList<>());
            }

            // Ajoutez le détail utilisateur à la liste des détails de l'utilisateur
            user.getDetailsUser().add(detailsUser);
            utilRepo.save(user);

            return detailsUser;
        } else {
            throw new EntityNotFoundException("User with email " + email + " not found");
        }
    }



    @Override
    public void delete(long id) {
        DetailsUser detailsUser = detailsUserRepository.getById(id);
        detailsUser.setStatus(false);
        detailsUserRepository.save(detailsUser);

    }

    public List<Object[]> getFavoriteSeasonStatistics() {
        return detailsUserRepository.findFavoriteSeasonStatistics();
    }
    public List<Object[]> getFavoriteLandscapeStatistics() {
        return detailsUserRepository.findFavoriteLandscapeStatistics();
    }
    public List<Object[]> getFavoriteAccommodationTypeStatistics() {
        return detailsUserRepository.findFavoriteAccommodationTypeStatistics();
    }

    public List<Product> recommendProducts(Long userId) {
        // Get all orders of the user
        List<Commande> commandes = commandeRepository.findByIdUtilisateur(userId);

        // Create a map to count category occurrences
        Map<CategoriesProduct, Integer> categoryCount = new HashMap<>();
        Set<String> productNames = new HashSet<>();
        Set<Long> purchasedProductIds = new HashSet<>();

        for (Commande commande : commandes) {
            for (Product product : commande.getProducts()) {
                CategoriesProduct category = product.getId_Categorie();
                categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
                productNames.add(product.getNomProduct());
                purchasedProductIds.add(product.getIdProduct());
            }
        }

        // Sort categories by count in descending order
        List<Map.Entry<CategoriesProduct, Integer>> sortedCategories = new ArrayList<>(categoryCount.entrySet());
        sortedCategories.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // Get top categories
        List<CategoriesProduct> topCategories = sortedCategories.stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<Product> recommendedProducts = new ArrayList<>();

        // Find products in top categories that the user has not purchased
        for (CategoriesProduct category : topCategories) {
            List<Product> products = productRepository.findByCategory(category);
            for (Product product : products) {
                if (!purchasedProductIds.contains(product.getIdProduct())) {
                    recommendedProducts.add(product);
                }
            }
        }

        // Find products with the same names that the user has not purchased
        for (String productName : productNames) {
            List<Product> products = productRepository.findByNomProduct(productName);
            for (Product product : products) {
                if (!purchasedProductIds.contains(product.getIdProduct())) {
                    recommendedProducts.add(product);
                }
            }
        }

        return recommendedProducts;
    }


    @Override
    public DetailsUser update(DetailsUser detailsUser) {
        return detailsUserRepository.save(detailsUser);
    }

}