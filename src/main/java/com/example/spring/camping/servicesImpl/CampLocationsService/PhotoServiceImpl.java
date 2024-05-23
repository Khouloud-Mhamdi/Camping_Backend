package com.example.spring.camping.servicesImpl.CampLocationsService;

import com.example.spring.camping.models.CampLocations.DetailCampSite;
import com.example.spring.camping.models.CampLocations.Photo;
import com.example.spring.camping.respositories.CampSiteRepositories.CampsiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.DetailCampSiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.PhotoRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.RuleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Slf4j
@Service
public class PhotoServiceImpl  {


    RuleRepository ruleRepository;
    CampsiteRepository campsiteRepo;
    DetailCampSiteRepository detailCampSiteRepository;
    PhotoRepository photoRepository;




    public Photo add(byte[] image,Long id_detail) {
        DetailCampSite detailCampSite= detailCampSiteRepository.findById(id_detail).get();
        Photo photo = new Photo();
        photo.setImage(image);
        photo.setDetailCampSites(detailCampSite);
        return photoRepository.save(photo);
    }




    public Photo get(Long id) {
        return photoRepository.getById(id);
    }

    public void delete(Long id) {
    photoRepository.deleteById(id);
    }

    public List<byte[]> getAllByDetailCampsite(Long id) {


        return photoRepository.findAllByDetailCampSiteId(id);
    }


    public void save(Photo image) {
        photoRepository.save(image);
    }

    public Optional<Photo> getOne(Long id) {
        return photoRepository.findById(id);
    }

    public List<String> list(Long id) {
        return photoRepository.findAllByDetailCampSiteId1(id);
    }
}
