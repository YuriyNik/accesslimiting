package com.assigment.config;

import com.assigment.controller.QuotaController;
import com.assigment.model.User;
import com.assigment.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    private static final String[] FIRST_NAMES = {"John", "Jane", "Alice", "Bob", "Charlie", "David", "Emma", "Eva", "Frank", "Grace"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};

    private static final Random random = new Random();

    public static String generateFirstName() {
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
    }

    public static String generateLastName() {
        return LAST_NAMES[random.nextInt(LAST_NAMES.length)];
    }

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

        for (int i=0;i<30;i++){
            User user = new User(generateFirstName(), generateLastName(), LocalDateTime.now().minusDays(1));
            //log.info("User created = ",userRepository.save(user));
            userRepository.save(user);
            log.info("User created = "+user);
        }
    }
}
