package dsa.practice.lld.paymentGateway.controller;

import dsa.practice.lld.paymentGateway.model.UserDto;
import dsa.practice.lld.paymentGateway.service.UserService;

public class UserController {
    UserService userService;
    public UserController() {
        this.userService = new UserService();
    }

    public UserDto addUser(UserDto userDto){
        return userService.addUser(userDto);
    }

    public UserDto getUserById(String id){
        return userService.getUserById(id);
    }

}
