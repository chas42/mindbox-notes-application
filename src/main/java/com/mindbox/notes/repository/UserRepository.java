package com.mindbox.notes.repository;

import com.mindbox.notes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
