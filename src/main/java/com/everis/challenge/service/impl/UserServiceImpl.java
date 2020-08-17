package com.everis.challenge.service.impl;

import com.everis.challenge.domain.User;
import com.everis.challenge.repository.UserRepository;
import com.everis.challenge.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public void save(User user) {
        log.info("Save in database - user: {}",user);
        userRepository.save(user);
    }
}
