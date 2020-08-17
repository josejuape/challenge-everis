package com.everis.challenge.repository;

import com.everis.challenge.model.thridparty.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM User u WHERE u.username = ?1", nativeQuery = true)
    User findByUsername(String username);

}
