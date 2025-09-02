package org.curs.AppServer.controllers;

import org.curs.AppServer.entities.User;
import org.curs.AppServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private static final Logger log = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @GetMapping("/id")
    public ResponseEntity<User> getUserById(@RequestParam Integer id) {
        log.info("getting user with id: " + id); //логирую инфу для контроля в терминале
        Optional<User> user = userService.findById(id); //

        if (user.isPresent()) return new ResponseEntity<>(user.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/login")
    public ResponseEntity<User> findByLogin(@RequestParam String login){
        log.info("getting user with login: " + login);
        Optional<User> user = userService.findByLogin(login);

        if (user.isPresent()) return new ResponseEntity<>(user.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<User> authenticate(@RequestParam String login, @RequestParam String password){
        log.info("authenticating user with login: " + login + " and password: " + password);
        Optional<User> user = userService.findByLogin(login);

        if(user.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(user.get().getPassword().equals(password)) return new ResponseEntity<>(user.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
