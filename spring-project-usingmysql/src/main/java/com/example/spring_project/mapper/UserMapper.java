package com.example.spring_project.mapper;

import com.example.spring_project.dto.UserDto;
import com.example.spring_project.entity.User;

public class UserMapper {
    public static UserDto maoToUserDto(User user){
        UserDto userDto=new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
        return userDto;
    }
    public static User mapToUser(UserDto userDto){
        User user= new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
        return user;
    }
}
