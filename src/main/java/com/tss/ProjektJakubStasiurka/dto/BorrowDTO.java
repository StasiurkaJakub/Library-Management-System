package com.tss.ProjektJakubStasiurka.dto;

import java.time.LocalDate;

public class BorrowDTO {

    private Long id;
    private String username;
    private Long bookId;
    private String bookTitle;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean returned;

    public BorrowDTO() {
    }

    public BorrowDTO(Long id, String username, Long bookId, String bookTitle,
            LocalDate borrowDate, LocalDate returnDate, boolean returned) {
        this.id = id;
        this.username = username;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.returned = returned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
