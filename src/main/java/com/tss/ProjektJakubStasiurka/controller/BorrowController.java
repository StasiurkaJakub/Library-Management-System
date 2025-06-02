package com.tss.ProjektJakubStasiurka.controller;

import com.tss.ProjektJakubStasiurka.dto.BorrowDTO;
import com.tss.ProjektJakubStasiurka.mapper.BorrowMapper;
import com.tss.ProjektJakubStasiurka.model.Book;
import com.tss.ProjektJakubStasiurka.model.User;
import com.tss.ProjektJakubStasiurka.service.BookService;
import com.tss.ProjektJakubStasiurka.service.BorrowService;
import com.tss.ProjektJakubStasiurka.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class BorrowController {

    private final BorrowService borrowService;
    private final UserService userService;
    private final BookService bookService;

    public BorrowController(BorrowService borrowService, UserService userService, BookService bookService) {
        this.borrowService = borrowService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("/borrows/borrow/{bookId}")
    public String borrowBook(@PathVariable Long bookId, Authentication authentication) {
        User user = (User) userService.loadUserByUsername(authentication.getName());
        Book book = bookService.findById(bookId);

        if (!book.isAvailable()) {
            throw new IllegalStateException("This book is already borrowed.");
        }

        borrowService.borrowBook(user, book);
        return "redirect:/books";
    }

    @GetMapping("/borrows/my-borrows")
    public String getUserBorrows(Model model, Authentication authentication) {
        Long userId = userService.findByUsername(authentication.getName()).getId();

        List<BorrowDTO> borrowsDto = borrowService.getUserBorrows(userId).stream()
                .map(BorrowMapper::toDTO)
                .collect(Collectors.toList());

        model.addAttribute("borrows", borrowsDto);
        return "my-borrows";
    }

    @PostMapping("/borrows/return/{borrowId}")
    public String returnBook(@PathVariable Long borrowId) {
        borrowService.returnBook(borrowId);
        return "redirect:/borrows/my-borrows";
    }

    //ADMIN ONLY ENDPOINTS
    @GetMapping("/admin/borrows")
    @PreAuthorize("hasRole('ADMIN')")
    public String manageBorrows(Model model) {
        List<BorrowDTO> borrowsDto = borrowService.getAllBorrows().stream()
                .map(BorrowMapper::toDTO)
                .collect(Collectors.toList());

        model.addAttribute("borrows", borrowsDto);
        return "borrow-list";
    }

}
