package com.customer.auth.controller;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DynamoDBTestController {

    private final AmazonDynamoDB amazonDynamoDB;

    public DynamoDBTestController(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    @GetMapping("/dynamodb-check")
    public String checkTables() {
        try {
            List<String> tables = amazonDynamoDB.listTables().getTableNames();
            return "🟢 Bağlantı başarılı. Tablolar: " + tables;
        } catch (Exception e) {
            return "🔴 Bağlantı hatası: " + e.getMessage();
        }
    }
}