package me.barbaraDev.service.impl;


import me.barbaraDev.domain.model.User;
import me.barbaraDev.domain.repository.UserRepository;
import me.barbaraDev.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private static final Long UNCHANGEABLE_USER_ID = 1L;

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll (){
        return this.userRepository.findAll();
    }
    @Transactional(readOnly = true)
    public User findById(Long id) {

        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public User create(User userToCreate) {
        if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())){
            throw new IllegalArgumentException("This Account number already exists.");
        }
        return userRepository.save(userToCreate);
    }

    @Transactional
    public User update (Long id, User userToUpdate){
        if(UNCHANGEABLE_USER_ID.equals(id)){
            throw new IllegalArgumentException("Cannot update user with ID " + UNCHANGEABLE_USER_ID);
        }
        User existingUser = userRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if (userToUpdate.getAccount() != null
                && userToUpdate.getAccount().getNumber() != null
                && !userToUpdate.getAccount().getNumber().equals(existingUser.getAccount().getNumber())
                && userRepository.existsByAccountNumber(userToUpdate.getAccount().getNumber())) {
            throw  new IllegalArgumentException("This account number already exists.");
        }

        userToUpdate.setId(id);
        return userRepository.save(userToUpdate);
    }
}
