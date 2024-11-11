package com.example.UserAuthentication.service;

import com.example.UserAuthentication.dto.UserDto;
import com.example.UserAuthentication.entity.User;
import com.example.UserAuthentication.repository.UserRepository;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public UserDto registerNewUser(UserDto userDto){

        User user = new User(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getPassword(),
                userDto.getRoles()
        );

        if(userRepository.findByUsername(user.getUserName())!= null){
            throw new RuntimeException("User Already Exists");
        }
        User user1 = userRepository.save(user);

        UserDto userDto1;
        userDto1 = new UserDto(
                user1.getId(),
                user1.getUserName(),
                user1.getPassword(),
                user1.getRoles()
        );

        return userDto1;

    }


    public UserDto getUserDetails(String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new Exception("User does not exists!!");
        }
        UserDto userDto = new UserDto(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getRoles()
        );

        return userDto;
    }

}
