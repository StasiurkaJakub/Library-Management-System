package com.tss.ProjektJakubStasiurka.controller;

import com.tss.ProjektJakubStasiurka.dto.BorrowDTO;
import com.tss.ProjektJakubStasiurka.service.BorrowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/borrows")
public class BorrowRestController {

    private final BorrowService borrowService;

    public BorrowRestController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BorrowDTO>> getAllBorrows() {
        return ResponseEntity.ok(borrowService.getAllBorrowsDTO());
    }
}
