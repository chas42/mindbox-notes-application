package com.mindbox.notes.service;

import com.mindbox.notes.model.User;

public interface UserService {

    void create(User user);
    User findById(Long id);

}
