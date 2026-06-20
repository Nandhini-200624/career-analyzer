package com.career.analyzer.controller;
import java.util.List;
import jakarta.validation.Valid;

import com.career.analyzer.entity.User;
import com.career.analyzer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public User register( @Valid @RequestBody User user) {
        return service.saveUser(user);
    }
    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return service.deleteUser(id);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody User user) {
        return service.updateUser(id, user);
    }



}