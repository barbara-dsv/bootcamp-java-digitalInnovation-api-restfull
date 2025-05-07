package me.barbaraDev.domain.service;

import me.barbaraDev.domain.model.User;

public interface UserService {

    User findId(Long id);
    User create(User userToCreate);
}
