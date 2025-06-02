package com.tss.ProjektJakubStasiurka.service;

import com.tss.ProjektJakubStasiurka.model.Borrow;
import com.tss.ProjektJakubStasiurka.model.User;
import com.tss.ProjektJakubStasiurka.repository.BorrowRepository;
import com.tss.ProjektJakubStasiurka.repository.UserRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BorrowRepository borrowRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, BorrowRepository borrowRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.borrowRepository = borrowRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
    }

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public long countUsers() {
        return userRepository.count();
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Borrow> userBorrows = borrowRepository.findByUserId(userId);
        for (Borrow borrow : userBorrows) {
            borrow.setUser(null);
            borrowRepository.save(borrow);
        }

        userRepository.delete(user);
    }
}
