package ru.gb.servise;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);
}
