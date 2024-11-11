package com.example.UserAuthentication.controller;

import com.example.UserAuthentication.dto.UserDto;
import com.example.UserAuthentication.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/")
@NoArgsConstructor
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("REGISTER")
    public ResponseEntity<UserDto> CREATE_USER(@Valid @RequestBody UserDto userDto){
        UserDto userDto1;
       userDto1= userService.registerNewUser(userDto);
       return new ResponseEntity<>(userDto1 , HttpStatus.CREATED);

    }
    @GetMapping("{UserName}")
    public ResponseEntity<UserDto> getUserLoginDetails(@PathVariable("UserName") String username) throws Exception {
        UserDto userDto = userService.getUserDetails(username);
        return new ResponseEntity<>(userDto , HttpStatus.OK);

    }


}
