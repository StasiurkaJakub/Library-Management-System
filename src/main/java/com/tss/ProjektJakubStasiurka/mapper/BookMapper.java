package com.tss.ProjektJakubStasiurka.mapper;

import com.tss.ProjektJakubStasiurka.dto.BookDTO;
import com.tss.ProjektJakubStasiurka.model.Book;

public class BookMapper {

    // Entity -> DTO
    public static BookDTO toDTO(Book book) {
        if (book == null) {
            return null;
        }
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.isAvailable()
        );
    }

    // DTO -> Entity (tylko pola edytowalne przez użytkownika)
    public static Book toEntity(BookDTO dto) {
        if (dto == null) {
            return null;
        }

        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());

        if (dto.getId() == null) {
            book.setAvailable(true);
        } else {
            book.setAvailable(dto.isAvailable());
        }
        return book;
    }
}
