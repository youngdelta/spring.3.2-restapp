package jpa.example.jsp_spring.repogitory;

import org.springframework.data.jpa.repository.JpaRepository;

import jpa.example.jsp_spring.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}