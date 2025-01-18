package com.slippery.contriop.service;

import com.slippery.contriop.dto.UserDto;
import com.slippery.contriop.models.Users;

public interface UserService {
    UserDto login(Users userDetails);
    UserDto register(Users userDetails);
    UserDto deleteUserById(Long userId);
    UserDto findUserById(Long userId);

}
