package dsa.practice.springBoot.controller;

import dsa.practice.springBoot.annotation.RequestInterceptor;
import dsa.practice.springBoot.component.UserComponent;
import dsa.practice.springBoot.entity.User;
import dsa.practice.springBoot.enums.Role;
import dsa.practice.springBoot.reposiory.UserRepository;
import dsa.practice.springBoot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    UserService userService;
    User user;

    public UserController(UserService userService, @Qualifier("ravi") User user) {
        this.userService = userService;
        this.user = user;
        logger.info("UserController created");
    }
    @GetMapping
    @RequestInterceptor(role = {Role.ADMIN, Role.SUPER_ADMIN})
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/bean")
    public ResponseEntity<User> getUserBean() {
        UserComponent userComponent = new UserComponent();
        userComponent.processOrder();
        return ResponseEntity.ok(user);
    }

}
