package com.example.spring.camping.servicesImpl.CampLocationsService;

import com.example.spring.camping.models.CampLocations.Photo;
import com.example.spring.camping.respositories.CampSiteRepositories.CampsiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.DetailCampSiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.PhotoRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.RuleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Slf4j
@Service
public class PhotoServiceImpl  {


    RuleRepository ruleRepository;
    CampsiteRepository campsiteRepo;
    DetailCampSiteRepository detailCampSiteRepository;
    PhotoRepository photoRepository;





    public Photo add(byte[] image) {
        Photo photo = new Photo();
        photo.setImage(image);
        return photoRepository.save(photo);
    }
    public Photo get(Long id) {
        return photoRepository.getById(id);
    }

    public void delete(Long id) {
    photoRepository.deleteById(id);
    }

}
