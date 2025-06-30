package com.customer.auth.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.customer.auth.model.User;

@Repository
public class UserRepository {
    
@Autowired
private DynamoDBMapper dynamoDBMapper;

    public void saveUser(Object user) {
        dynamoDBMapper.save(user);
    }

    public String createUser(User user) {
        dynamoDBMapper.save(user);
        return user.getUsername();
    }

    public User getUserById(String username) {
        return dynamoDBMapper.load(User.class, username);
    }


}
