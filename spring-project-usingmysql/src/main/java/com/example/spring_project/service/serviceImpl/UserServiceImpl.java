package com.example.spring_project.service.serviceImpl;


import com.example.spring_project.dto.UserDto;
import com.example.spring_project.entity.User;
import com.example.spring_project.mapper.UserMapper;
import com.example.spring_project.repository.UserRepository;
import com.example.spring_project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser= userRepository.findById(userId);
        User user= optionalUser.get();
        return UserMapper.maoToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUser=userRepository.findAll();
        return allUser.stream().map(UserMapper::maoToUserDto).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updateUser=userRepository.save(existingUser);
        return UserMapper.maoToUserDto(updateUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        //Convert user dto to user jpa entity
        User user= UserMapper.mapToUser(userDto);
        User savedUser= userRepository.save(user);
        //I need to return user dto

        UserDto userDto1=UserMapper.maoToUserDto(savedUser);
        return userDto1;
    }
}
