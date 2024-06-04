package com.example.spring.camping.servicesImpl;

import com.example.spring.camping.models.ManageUsers.DetailsUser;
import com.example.spring.camping.models.ManageUsers.Role;
import com.example.spring.camping.models.ManageUsers.User;
import com.example.spring.camping.respositories.DetailsUserRepository;
import com.example.spring.camping.respositories.UserRepository;
import com.example.spring.camping.services.ICrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailsUserServiceImpl implements ICrud<DetailsUser> {

    @Autowired
    private DetailsUserRepository detailsUserRepository;
    @Autowired
    private UserRepository utilRepo ;
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
        detailsUser.setStatus(true);
        detailsUserRepository.save(detailsUser);
        u.get().setDetailsUser(detailsUser);
        utilRepo.save(u.get());
        return detailsUser;
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



    @Override
    public DetailsUser update(DetailsUser detailsUser) {
        return detailsUserRepository.save(detailsUser);
    }

}