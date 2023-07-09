package com.project.Bookmyshow.Repository;

import com.project.Bookmyshow.Module.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
     User findByEmail(String email);
}
