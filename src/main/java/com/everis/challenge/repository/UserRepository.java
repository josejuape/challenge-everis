package com.everis.challenge.repository;

import com.everis.challenge.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends MongoRepository<User, Long> {

    UserDetails findByUsername(String username);

}
