package com.mfr.usersManager.Config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Value("${com.mfr.users-manager.mongo.mongoUri}")
    private String mongoUri;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(new MongoClientURI(mongoUri));
    }

    @Override
    protected String getDatabaseName() {
        return "usersDb";
    }
}
