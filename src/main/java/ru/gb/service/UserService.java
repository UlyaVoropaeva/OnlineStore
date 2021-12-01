package ru.gb.service;


import org.springframework.stereotype.Service;
import ru.gb.entity.User;

import java.util.Optional;

@Service
public interface UserService {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User saveUser(User user);

}
