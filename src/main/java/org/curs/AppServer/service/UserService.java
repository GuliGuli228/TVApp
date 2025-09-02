package org.curs.AppServer.service;

import org.curs.AppServer.entities.User;
import org.curs.AppServer.repository.CustomerRepository;
import org.curs.AppServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Integer id) {
        logger.info("finding user with id: " + id);
        return userRepository.findById(id);
    }

    public Optional<User> findByLogin(String login) {
        logger.info("finding user with login: " + login);
        return userRepository.findByLogin(login);
    }
}
