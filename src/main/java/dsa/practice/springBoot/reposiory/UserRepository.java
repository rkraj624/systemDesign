package dsa.practice.springBoot.reposiory;

import dsa.practice.springBoot.entity.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Lazy
@Component
public class UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    List<User> users = new ArrayList<>();


    @PostConstruct
    public void init() {
        for(int i=0; i<10; i++) {
            users.add(new User("User" + i, "@123"));
        }
        logger.info("UserRepository initialized");
    }

    @PreDestroy
    public void destroy() {
        logger.info("UserRepository destroyed");
    }


    public User save(User user) {
        users.add(user);
        return user;
    }

    public List<User> findAll() {
        return users;
    }
}
