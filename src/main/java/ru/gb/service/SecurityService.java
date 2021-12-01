package ru.gb.service;

import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);

}
