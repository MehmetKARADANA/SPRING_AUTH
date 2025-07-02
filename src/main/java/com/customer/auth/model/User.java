package com.customer.auth.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

@DynamoDBTable(tableName = "User")
public class User {

	
    @JsonProperty("username")
    @DynamoDBHashKey(attributeName = "username")
    private String username;

	
    @JsonProperty("password")
    @DynamoDBAttribute(attributeName = "password")
    private String password;

    public User() {} 
	
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
