package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.springboot.model.Image;
import com.springboot.service.ImageCrudService;


@RestController
@RequestMapping("/dynamo")
public class ImageCrudController {


	private ImageCrudService imageCrudService;

	    @Autowired
	    public ImageCrudController(ImageCrudService imageCrudService) {
	        this.imageCrudService = imageCrudService;
	    }

	    @RequestMapping(value = "/images", produces = {"application/json"}, method = RequestMethod.POST)
	    public ResponseEntity createUser(@RequestBody Image image) {
	        try {
	            Image response = imageCrudService.createImage(image);
	            return ResponseEntity.status(HttpStatus.CREATED).body(response);
	        } catch (AmazonServiceException e) {
	            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
	        } catch (AmazonClientException e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
	        }
	    }
	    
	    @RequestMapping(value = "/images", produces = {"application/json"}, method = RequestMethod.GET)
	    public ResponseEntity getUsers(@RequestBody String userHash) {
	        try {
	            List<Image> response = imageCrudService.getImages(userHash);
	            return ResponseEntity.status(HttpStatus.OK).body(response);
	        } catch (AmazonServiceException e) {
	            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
	        } catch (AmazonClientException e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
	        }
	    }
		
	    @RequestMapping(value = "/images/{imageId}", produces = {"application/json"}, method = RequestMethod.GET)
	    public ResponseEntity readUser(@PathVariable String imageId) {
	        try {
	            Image response = imageCrudService.readImage(imageId);
	            return ResponseEntity.status(HttpStatus.OK).body(response);
	        } catch (AmazonServiceException e) {
	            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
	        } catch (AmazonClientException e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
	        }
	    }
}
