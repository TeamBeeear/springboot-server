package com.project.gomgom.user.service;

import com.project.gomgom.user.dto.UserDto;

public interface UserService {
    UserDto signup(UserDto userDto);
    UserDto login(UserDto userDto) throws Exception;
}
