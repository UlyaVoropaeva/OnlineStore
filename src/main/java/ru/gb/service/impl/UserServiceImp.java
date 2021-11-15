package ru.gb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.entity.Role;
import ru.gb.entity.User;
import ru.gb.repository.RoleRepository;
import ru.gb.repository.UserRepository;
import ru.gb.service.UserService;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String USER_ROLE = "ROLE_USER";

    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setRoles((Collection<Role>) roleRepository.findByRole(USER_ROLE));
        return userRepository.saveAndFlush(user);
    }
}