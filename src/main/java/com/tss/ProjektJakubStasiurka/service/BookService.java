package com.tss.ProjektJakubStasiurka.service;

import com.tss.ProjektJakubStasiurka.dto.BookDTO;
import com.tss.ProjektJakubStasiurka.mapper.BookMapper;
import com.tss.ProjektJakubStasiurka.model.Book;
import com.tss.ProjektJakubStasiurka.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BorrowService borrowService;
    private final NotificationService notificationService;

    public BookService(BookRepository bookRepository, BorrowService borrowService, NotificationService notificationService) {
        this.bookRepository = bookRepository;
        this.borrowService = borrowService;
        this.notificationService = notificationService;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        boolean isNew = (book.getId() == null);
        Book saved = bookRepository.save(book);
        if (isNew) {
            String msg = "Title: '" + saved.getTitle() + "' Author: " + saved.getAuthor();
            notificationService.sendNewBookNotification(msg);
        }
        return saved;
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void deleteById(Long id) {
        borrowService.deleteBorrowByBookId(id);
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooks(String query) {
        return bookRepository.searchBooks(query);
    }

    public List<BookDTO> findAllDTO() {
        return bookRepository.findAll().stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BookDTO findDTOById(Long id) {
        return BookMapper.toDTO(findById(id));
    }

    public BookDTO saveDTO(BookDTO bookDTO) {
        Book book = BookMapper.toEntity(bookDTO);
        return BookMapper.toDTO(save(book));
    }
}
