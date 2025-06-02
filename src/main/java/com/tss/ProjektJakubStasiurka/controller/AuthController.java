package com.tss.ProjektJakubStasiurka.controller;

import com.tss.ProjektJakubStasiurka.dto.RegistrationDTO;
import com.tss.ProjektJakubStasiurka.model.User;
import com.tss.ProjektJakubStasiurka.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegistrationDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") RegistrationDTO registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(registrationDto.getPassword());
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/whoami")
    public String showAuthInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAnonymous = auth instanceof AnonymousAuthenticationToken;

        model.addAttribute("authInfo", auth);
        model.addAttribute("anonymous", isAnonymous);
        return "auth-info";
    }

    @GetMapping({"/login", "/"})
    public String login(@RequestParam(required = false) String error,
            @RequestParam(required = false) String logout,
            Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password!");
        }
        if (logout != null) {
            model.addAttribute("message", "Sign out successful!");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAnonymous = auth instanceof AnonymousAuthenticationToken;
        boolean isAdmin = false;

        if (!isAnonymous && auth.isAuthenticated()) {
            isAdmin = auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
            model.addAttribute("authInfo", auth);
            model.addAttribute("isAdmin", isAdmin);
            model.addAttribute("anonymous", isAnonymous);
            return "redirect:/homepage";
        }

        return "login";
    }
}
