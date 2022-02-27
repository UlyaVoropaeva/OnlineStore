package ru.gb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.entity.User;
import ru.gb.ropositor.RoleRepositoryAuth;
import ru.gb.ropositor.UserRepositoryAuth;


import java.util.Optional;

@Service
public class UserDetailsServiceImplAuth implements UserDetailsService {

   private final RoleRepositoryAuth roleRepository;
   private final UserRepositoryAuth userRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserDetailsServiceImplAuth(RoleRepositoryAuth roleRepository, UserRepositoryAuth userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        Optional<User> mayBeUser = userRepository.findByEmail(userName);
        if (mayBeUser.isEmpty()) {
            throw new UsernameNotFoundException("User cannot be fount  by passed name: " + userName);
        }
        User user = mayBeUser.get();

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .toList()
        );
    }
}