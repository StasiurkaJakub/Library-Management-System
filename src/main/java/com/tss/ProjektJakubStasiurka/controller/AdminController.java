package com.tss.ProjektJakubStasiurka.controller;

import com.tss.ProjektJakubStasiurka.dto.UserDTO;
import com.tss.ProjektJakubStasiurka.mapper.UserMapper;
import com.tss.ProjektJakubStasiurka.service.BorrowService;
import com.tss.ProjektJakubStasiurka.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final BorrowService borrowService;

    public AdminController(
            UserService userService,
            BorrowService borrowService
    ) {
        this.userService = userService;
        this.borrowService = borrowService;
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminPanel(Model model) {
        model.addAttribute("userCount", userService.countUsers());
        model.addAttribute("activeBorrows", borrowService.getActiveBorrows().size());
        return "admin-panel";
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public String manageUsers(Model model) {
        List<UserDTO> usersDto = userService.findAll().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authInfo", auth);
        model.addAttribute("users", usersDto);
        return "user-list";
    }

    @PostMapping("/users/delete/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable Long userId) {
        borrowService.returnAllBooksByUser(userId);
        userService.deleteUser(userId);
        return "redirect:/admin/users";
    }

}
