package com.jmv.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jmv.model.User;

@Repository
public interface UserMongoRepository extends MongoRepository<User, Integer>{

}
