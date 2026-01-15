package dsa.practice.lld.paymentGateway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dsa.practice.lld.paymentGateway.model.User;
import dsa.practice.lld.paymentGateway.model.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService {
    public static List<User> userList = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

    public UserDto addUser(UserDto userDto){
        User user = mapper.convertValue(userDto, User.class);
        user.setId(userDto.getName()+"-"+UUID.randomUUID());
        userList.add(user);
        return mapper.convertValue(user, UserDto.class);
    }

    public UserDto getUserById(String id){
        for(User user : userList){
            if(user.getId().equals(id)){
                return mapper.convertValue(user, UserDto.class);
            }
        }
        return null;
    }

}
