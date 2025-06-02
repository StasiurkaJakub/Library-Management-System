package com.tss.ProjektJakubStasiurka.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/homepage")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authInfo", auth);

        boolean isAnonymous = auth instanceof AnonymousAuthenticationToken;
        boolean isAdmin = false;

        if (!isAnonymous && auth.isAuthenticated()) {
            isAdmin = auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        }

        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("anonymous", isAnonymous);
        return "home";
    }

}
