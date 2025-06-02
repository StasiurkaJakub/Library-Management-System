package com.tss.ProjektJakubStasiurka.controller;

import com.tss.ProjektJakubStasiurka.dto.BookDTO;
import com.tss.ProjektJakubStasiurka.mapper.BookMapper;
import com.tss.ProjektJakubStasiurka.model.Book;
import com.tss.ProjektJakubStasiurka.service.BookService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String listBooks(Model model, Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        List<BookDTO> booksDto = bookService.findAll().stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());

        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("books", booksDto);
        return "books";
    }

    @GetMapping("/books/search")
    public String searchBooks(@RequestParam String query, Model model, Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        List<BookDTO> booksDto = bookService.searchBooks(query).stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());

        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("books", booksDto);
        model.addAttribute("searchQuery", query);
        return "books";
    }

    // ADMIN ONLY ENDPOINTS
    @GetMapping("/admin/books/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddForm(Model model) {
        model.addAttribute("book", new BookDTO());
        return "book-form";
    }

    @PostMapping("/admin/books/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveBook(@ModelAttribute("book") BookDTO bookDto) {
        if (bookDto.getId() != null) {
            Book existing = bookService.findById(bookDto.getId());
            bookDto.setAvailable(existing.isAvailable());
        } else {
            bookDto.setAvailable(true);
        }

        Book bookEntity = BookMapper.toEntity(bookDto);
        bookService.save(bookEntity);
        return "redirect:/books";
    }

    @GetMapping("/admin/books/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book existing = bookService.findById(id);
        BookDTO dto = BookMapper.toDTO(existing);
        model.addAttribute("book", dto);
        return "book-form";
    }

    @GetMapping("/admin/books/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}
