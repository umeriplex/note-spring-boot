package com.tecqasr.notes.app.tecqasrnotes.repositories;

import com.tecqasr.notes.app.tecqasrnotes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
