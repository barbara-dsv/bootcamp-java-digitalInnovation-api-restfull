package me.barbaraDev.service;

import me.barbaraDev.domain.model.User;

public interface UserService {

    User findById(Long id);
    User create(User userToCreate);
}
