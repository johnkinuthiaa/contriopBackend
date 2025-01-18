package com.slippery.contriop.service.Implementations;

import com.slippery.contriop.dto.UserDto;
import com.slippery.contriop.models.Users;
import com.slippery.contriop.repository.UserRepository;
import com.slippery.contriop.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder(12);

    public UserServiceImplementation(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDto login(Users userDetails) {
        UserDto response =new UserDto();
        Optional<Users> existingByUsername = Optional.ofNullable(repository.findByUsername(userDetails.getUsername()));
        List<Users> existingByEmail= repository.findAll().stream()
                .filter(users -> users.getEmail().equalsIgnoreCase(userDetails.getEmail()))
                .toList();

        if(!existingByEmail.isEmpty()){
            response.setMessage("User email already exists! please use another email");
            response.setStatusCode(200);
            return response;

        }
        if(existingByUsername.isEmpty()){
            response.setMessage("Username already exists! please use a unique name");
            response.setStatusCode(200);
            return response;

        }
        userDetails.setApiLimit(0);
        userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        repository.save(userDetails);

        response.setMessage("user saved ");
        response.setUser(userDetails);
        response.setStatusCode(200);
        return response;
    }

    @Override
    public UserDto register(Users userDetails) {
        return null;
    }

    @Override
    public UserDto deleteUserById(Long userId) {
        return null;
    }

    @Override
    public UserDto findUserById(Long userId) {
        return null;
    }
}
