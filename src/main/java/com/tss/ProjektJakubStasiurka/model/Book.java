package com.tss.ProjektJakubStasiurka.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    @OneToMany(mappedBy = "book")
    private List<Borrow> borrows = new ArrayList<>();

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable = true;

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }

    public void addBorrow(Borrow borrow) {
        borrows.add(borrow);
        borrow.setBook(this);
    }
}
