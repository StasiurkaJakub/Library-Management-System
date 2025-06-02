package com.tss.ProjektJakubStasiurka.service;

import com.tss.ProjektJakubStasiurka.dto.BorrowDTO;
import com.tss.ProjektJakubStasiurka.mapper.BorrowMapper;
import com.tss.ProjektJakubStasiurka.model.Borrow;
import com.tss.ProjektJakubStasiurka.model.User;
import com.tss.ProjektJakubStasiurka.model.Book;
import com.tss.ProjektJakubStasiurka.repository.BookRepository;
import com.tss.ProjektJakubStasiurka.repository.BorrowRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final UserService userService;
    private final NotificationService notificationService;

    public BorrowService(BorrowRepository borrowRepository, BookRepository bookRepository, UserService userService, NotificationService notificationService) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.notificationService = notificationService;

    }

    public Borrow borrowBook(User user, Book book) {
        book.setAvailable(false);
        Borrow borrow = new Borrow(user, book);
        return borrowRepository.save(borrow);
    }

    public List<Borrow> getUserBorrows(Long userId) {
        return borrowRepository.findByUserId(userId);
    }

    public List<Borrow> getActiveBorrows() {
        return borrowRepository.findByReturnedFalse();
    }

    public void returnBook(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new RuntimeException("Borrow not found"));
        borrow.returnBook();
        borrowRepository.save(borrow);
        String msg = "Book Returned: " + borrow.getBook().getTitle();
        notificationService.sendReturnedBookNotification(msg);

    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAllByOrderByBorrowDateDesc();
    }

    public void returnAllBooksByUser(Long userId) {
        List<Borrow> borrows = borrowRepository.findAllByUser_IdAndReturnedFalse(userId);

        for (Borrow borrow : borrows) {
            borrow.returnBook();
            bookRepository.save(borrow.getBook());
            borrowRepository.save(borrow);

            String msg = "Book Returned: " + borrow.getBook().getTitle();
            notificationService.sendReturnedBookNotification(msg);
        }
    }

    public void deleteBorrowByBookId(Long id) {
        List<Borrow> borrows = borrowRepository.findByBookId(id);

        for (Borrow borrow : borrows) {
            if (!borrow.isReturned()) {
                borrow.returnBook();
                bookRepository.save(borrow.getBook());
                borrowRepository.delete(borrow);
            }
        }

    }

    public BorrowDTO borrowBook(String username, Long bookId) {
        User user = userService.findByUsername(username);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is not available");
        }

        book.setAvailable(false);
        bookRepository.save(book);
        Borrow borrow = new Borrow(user, book);
        return BorrowMapper.toDTO(borrowRepository.save(borrow));
    }

    public List<BorrowDTO> getAllBorrowsDTO() {
        return borrowRepository.findAll().stream()
                .map(BorrowMapper::toDTO)
                .collect(Collectors.toList());
    }
}
