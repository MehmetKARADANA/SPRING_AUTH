package com.customer.auth.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.customer.auth.model.User;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.customer.auth.repository")
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.region}")
    private String region;
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
         return AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, region) // Change to your DynamoDB endpoint
            ).withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("dummy", "dummy")))
            .build();
    }

    @Bean
    @Primary
    public DynamoDBMapper dynamoMapper() {
        return new DynamoDBMapper(amazonDynamoDB());
    

    }

     @Bean
    public ApplicationRunner createTableIfNotExists(DynamoDBMapper mapper, AmazonDynamoDB client) {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                if (!client.listTables().getTableNames().contains("User")) {
                    CreateTableRequest tableRequest = mapper.generateCreateTableRequest(User.class);
                    tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
                    client.createTable(tableRequest);
                    System.out.println("✅ User tablosu oluşturuldu.");
                } else {
                    System.out.println("ℹ️ User tablosu zaten mevcut.");
                }
            }
        };
    }

 /*   @Bean
    public ApplicationRunner initializeDynamoDB(DynamoDBMapper mapper, AmazonDynamoDB client) {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                if (!client.listTables().getTableNames().contains("User")) {
                    CreateTableRequest request = mapper.generateCreateTableRequest(User.class);
                    request.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
                    client.createTable(request);
                    System.out.println("✅ User tablosu başarıyla oluşturuldu.");
                } else {
                    System.out.println("ℹ️ User tablosu zaten mevcut.");
                }
            }
        };
    }*/


}