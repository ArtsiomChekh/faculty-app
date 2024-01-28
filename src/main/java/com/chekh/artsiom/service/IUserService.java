package com.chekh.artsiom.service;

import com.chekh.artsiom.model.User;

import java.util.Optional;

public interface IUserService {
    Long saveUser(User user);

    Optional<User> findUserByEmail(String email);
}
