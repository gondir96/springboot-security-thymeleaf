package com.gondir.book.service;

import com.gondir.book.dto.UserDto;
import com.gondir.book.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUserDto();

    List<User> findAllUser();

    void deleteUserById(Long id);
}
