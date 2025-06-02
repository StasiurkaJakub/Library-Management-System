package com.tss.ProjektJakubStasiurka.mapper;

import com.tss.ProjektJakubStasiurka.dto.BorrowDTO;
import com.tss.ProjektJakubStasiurka.model.Borrow;

public class BorrowMapper {

    // Entity -> DTO
    public static BorrowDTO toDTO(Borrow borrow) {
        if (borrow == null) {
            return null;
        }
        return new BorrowDTO(
                borrow.getId(),
                borrow.getUsername(),
                borrow.getBook().getId(),
                borrow.getBook().getTitle(),
                borrow.getBorrowDate(),
                borrow.getReturnDate(),
                borrow.isReturned()
        );
    }
}
