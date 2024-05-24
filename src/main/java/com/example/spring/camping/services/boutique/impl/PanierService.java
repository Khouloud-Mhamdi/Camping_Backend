package com.example.spring.camping.services.boutique.impl;


import com.example.spring.camping.models.boutique.Panier;
import com.example.spring.camping.models.boutique.Product;
import com.example.spring.camping.respositories.boutique.PanierRepository;
import com.example.spring.camping.respositories.boutique.ProductRepository;
import com.example.spring.camping.services.boutique.IPanierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PanierService implements IPanierService {
    PanierRepository panierRepository;
    ProductRepository productRepository;
    @Override
    public List<Panier> retrieveAllPanier() {
        return panierRepository.findAll();
    }

    @Override
    public void addPanier(Panier f) throws Exception {
        Panier panier = panierRepository.findByProduitIdProduct(f.getProduit().getIdProduct());
        Product product = productRepository.findById(f.getProduit().getIdProduct()).get();
        if(product.getQuantiter()>=f.getQuantiter()) {
            if (panier != null) {
                panier.setQuantiter(panier.getQuantiter() + f.getQuantiter());
                panierRepository.save(panier);

            } else {
                panierRepository.save(f);
            }
            product.setQuantiter( product.getQuantiter() - f.getQuantiter());
            productRepository.save(product);
        }else{
            throw new Exception("Insufficient quantity");
        }
    }

    @Override
    public Panier updatePanier(Panier f) throws Exception {
        Panier panier = panierRepository.findByProduitIdProduct(f.getProduit().getIdProduct());
        Product product = productRepository.findById(f.getProduit().getIdProduct()).get();
        if(f.getQuantiter()==0){
            throw new Exception("can't modify quanity with 0 value");
        }
        if(panier.getQuantiter()>f.getQuantiter()){
            product.setQuantiter( product.getQuantiter() + Math.abs(panier.getQuantiter()-f.getQuantiter()));
            productRepository.save(product);
            panier.setQuantiter( f.getQuantiter());
            panierRepository.save(panier);
        }
        else {
            if(product.getQuantiter()>= Math.abs(panier.getQuantiter()-f.getQuantiter())) {
                    product.setQuantiter( product.getQuantiter() - Math.abs(panier.getQuantiter()-f.getQuantiter()));
                    productRepository.save(product);
                    panier.setQuantiter( f.getQuantiter());
                    panierRepository.save(panier);
                } else{
                throw new Exception("Insufficient quantity");
            }
        }
        return panier;
    };

    @Override
    public Panier retrievePanier(Long idPanier) {
        return panierRepository.findById(idPanier).orElse(null);
    }

    @Override
    public Panier deletePanierbyProductId(Long idProduit) {
       Panier panier = panierRepository.findByProduitIdProduct(idProduit);
       panier.getProduit().setQuantiter( panier.getProduit().getQuantiter() + panier.getQuantiter());
       productRepository.save(panier.getProduit());
       panierRepository.delete(panier);
       return panier;
    }
}
