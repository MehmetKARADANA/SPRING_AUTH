package com.customer.auth.repository;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

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

    public User getUserById(String id) {
        return dynamoDBMapper.load(User.class, id);
    }


}
