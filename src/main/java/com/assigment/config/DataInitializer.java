package com.assigment.config;

import com.assigment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        // Insert sample data into the database
        initializeUsers();
    }

    private void initializeUsers() {
     /*   User user1 = new User("John", "Doe", LocalDateTime.now().minusDays(5));
        User user2 = new User("Jane", "Smith", LocalDateTime.now().minusDays(3));
        User user3 = new User("Alice", "Johnson", LocalDateTime.now().minusDays(1));

        // Save users to the database
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);*/
    }
}
