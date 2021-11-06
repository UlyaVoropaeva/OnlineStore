package ru.gb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.entity.User;
import ru.gb.repository.UserRepository;
import ru.gb.servise.SecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "/auth")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response,  authentication);
        }
        request.getSession().invalidate();

        return "redirect:/login?logout";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("userForm", new User());

        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm")Model model, HttpServletRequest request, User userForm, BindingResult bindingResult) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }
        if (email.equals(userForm.getEmail()) || password.equals(userForm.getPassword())){
            model.addAttribute("error", "Your username and password is invalid.");
            return "auth/registration";
        }
        userRepository.save(userForm);
        securityService.autoLogin(userForm.getEmail(), userForm.getPassword());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout,  HttpSession session) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "auth/login";
    }
    @PostMapping("/login")
    public String userLogin (HttpServletRequest request, HttpServletResponse response){

        return "auth/login";
    }

    @GetMapping({"/", "/index"})
    public String welcome (Model model, HttpSession session) {
        return "/index";
    }

}
