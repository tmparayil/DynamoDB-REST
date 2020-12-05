package com.springboot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.springboot.model.Image;

@Component
public class ImageCrudDao {

	private DynamoDBMapper dynamoDBMapper;

    @Autowired
    public ImageCrudDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Image createUser(Image image) {
        dynamoDBMapper.save(image);
        return image;
    }

    public Image readImage(String imageId) {
        return dynamoDBMapper.load(Image.class, imageId);
    }
    
    public List<Image> getImages(String userHash) {
    	
    	List<Image> imageList = new ArrayList<>();
    	Map<String, AttributeValue> attributeValues = new HashMap<>();
        attributeValues.put(":userHash", new AttributeValue(userHash));
    	DynamoDBQueryExpression<Image> queryExpression = new DynamoDBQueryExpression<Image>()               
                .withFilterExpression("userHash = :userHash")
                .withExpressionAttributeValues(attributeValues);
    	
    	imageList = dynamoDBMapper.query(Image.class, queryExpression);
    	return imageList;
    	
    }
	
}
