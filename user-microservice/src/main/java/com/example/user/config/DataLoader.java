package com.example.user.config;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.stream.IntStream;
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepo;
    @Override
    public void run(String... args) throws Exception {
        if (userRepo.count() == 0) {
            IntStream.rangeClosed(1, 100).forEach(i -> {
                User user = User.builder()
                        .username("User" + i)
                        .email("user" + i + "@example.com")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .isActive(true)
                        .build();
                userRepo.save(user);
            });
        }
    }
}
