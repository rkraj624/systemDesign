package dsa.practice.springBoot.service;

import dsa.practice.springBoot.entity.User;
import dsa.practice.springBoot.reposiory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        return new UserRepository().save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
