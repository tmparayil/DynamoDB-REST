package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.model.Image;

@Service
public class ImageCrudService {
	
	private ImageCrudDao imageCrudDao;
	
	@Autowired
    public ImageCrudService(ImageCrudDao imageCrudDao) {
        this.imageCrudDao = imageCrudDao;
    }

    public Image createImage(Image image) {
        return imageCrudDao.createUser(image);
    }

    public Image readImage(String imageId) {
        return imageCrudDao.readImage(imageId);
    }
    
    public List<Image> getImages(String userHash) {
    	return imageCrudDao.getImages(userHash);
    }
	
}
