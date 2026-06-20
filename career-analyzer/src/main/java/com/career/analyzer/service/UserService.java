package com.career.analyzer.service;
import java.util.List;

import com.career.analyzer.entity.User;
import com.career.analyzer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User saveUser(User user) {
        return repo.save(user);
    }
    public List<User> getAllUsers() {
        return repo.findAll();
    }
    public User getUserById(Long id) {

        return repo
                .findById(id)
                .orElseThrow();
    }
    public String deleteUser(Long id) {
        repo.deleteById(id);
        return "User deleted successfully";
    }
    public User updateUser(Long id, User updatedUser) {
        User user = repo.findById(id).orElse(null);

        if(user != null) {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setRole(updatedUser.getRole());

            return repo.save(user);
        }

        return null;
    }
}