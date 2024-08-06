package com.example.backendstage.Repository;

import com.example.backendstage.Entity.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<user,Integer> {

}
