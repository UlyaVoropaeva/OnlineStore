package ru.gb.ropositor;

import org.springframework.data.repository.CrudRepository;
import ru.gb.entity.User;

import java.util.Optional;

public interface UserRepositoryAuth extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String username);
}