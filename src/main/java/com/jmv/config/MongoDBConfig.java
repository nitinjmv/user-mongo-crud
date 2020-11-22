package com.jmv.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.jmv.model.User;
import com.jmv.repository.UserMongoRepository;


@EnableMongoRepositories(basePackageClasses = UserMongoRepository.class)
@Configuration
public class MongoDBConfig {


    @Bean
    CommandLineRunner commandLineRunner(UserMongoRepository userRepository) {
        return strings -> {
            userRepository.save(new User(1, "Peter", "Peter@"));
            userRepository.save(new User(2, "Sam", "sam@"));
        };
    }

}