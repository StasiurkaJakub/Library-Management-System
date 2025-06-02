package com.tss.ProjektJakubStasiurka.repository;

import com.tss.ProjektJakubStasiurka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Override
    Optional<User> findById(Long id);
}
