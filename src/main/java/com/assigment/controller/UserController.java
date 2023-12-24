package com.assigment.controller;

import com.assigment.model.User;
import com.assigment.service.QuotaService;
import com.assigment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuotaService quotaService;

    @GetMapping
    public Iterable<User> getAllUsers() {
        System.out.println("get all");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        return user != null
                ? ResponseEntity.ok(user)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println("create="+user);
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        return user != null
                ? ResponseEntity.ok(user)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/consumeQuota/{userId}")
    public ResponseEntity<String> consumeQuota(@PathVariable String userId) {
        if (quotaService.consumeQuota(userId)) {
            return ResponseEntity.ok("Quota consumed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Quota exceeded");
        }
    }

    @GetMapping("/getUsersQuota")
    public ResponseEntity<Map<String, Integer>> getUsersQuota() {
        Map<String, Integer> usersQuota = quotaService.getUsersQuota();
        return ResponseEntity.ok(usersQuota);
    }

}
