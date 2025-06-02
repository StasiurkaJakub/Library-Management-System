package com.tss.ProjektJakubStasiurka.repository;

import com.tss.ProjektJakubStasiurka.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    List<Borrow> findByUserId(Long userId);

    List<Borrow> findByBookId(Long bookId);

    List<Borrow> findByReturnedFalse();

    List<Borrow> findAllByOrderByBorrowDateDesc();

    List<Borrow> findAllByUserIdAndReturnedFalse(Long userId);

    List<Borrow> findAllByUser_IdAndReturnedFalse(Long userId);

}
