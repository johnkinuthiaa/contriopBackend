package com.slippery.contriop.controller;

import com.slippery.contriop.dto.UserDto;
import com.slippery.contriop.models.Users;
import com.slippery.contriop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody Users user){
        return ResponseEntity.ok(userService.register(user));
    }
    @PutMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody Users user){
        return ResponseEntity.ok(userService.login(user));
    }
}
