package com.mindbox.notes.service;

import com.mindbox.notes.model.User;
import com.mindbox.notes.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {

        userRepository.save(user);

    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


}
