package ru.gb.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gb.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    User saveAndFlush(User user);
}