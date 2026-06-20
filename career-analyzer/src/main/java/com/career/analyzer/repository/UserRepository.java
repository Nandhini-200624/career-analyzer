package com.career.analyzer.repository;

import com.career.analyzer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}