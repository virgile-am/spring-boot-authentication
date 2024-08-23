package com.spring_boot_authentication.spring_boot_authentication.repository;

import com.spring_boot_authentication.spring_boot_authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
