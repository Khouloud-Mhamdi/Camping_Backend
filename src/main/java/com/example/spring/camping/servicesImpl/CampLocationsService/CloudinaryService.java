package com.example.spring.camping.servicesImpl.CampLocationsService;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
public class CloudinaryService {

Cloudinary cloudinary;

public CloudinaryService(){
    Map<String,String> valueMap=new HashMap<>();
    valueMap.put("cloud_name","dutl1lzfw");
    valueMap.put("api_key","998772742728239");
    valueMap.put("api_secret","NbkYo8jXs7G8_z2wDDB1-wWARz0");
    cloudinary=new Cloudinary(valueMap);

}
public Map upload(MultipartFile multipartFile)throws IOException{
    File file=convert(multipartFile);
    Map result=cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
    if(!Files.deleteIfExists(file.toPath())){
        throw new IOException("failed to delete temporary file" +file.getAbsolutePath());

    }
    return result;
}



private File convert(MultipartFile multipartFile)throws IOException{
    File file=new File((Objects.requireNonNull(multipartFile.getOriginalFilename())));
    FileOutputStream fo=new FileOutputStream(file);
    fo.write(multipartFile.getBytes());
    fo.close();
    return file;
}

public Map delete (String id) throws IOException{
    return cloudinary.uploader().destroy(id,ObjectUtils.emptyMap());
}



}
