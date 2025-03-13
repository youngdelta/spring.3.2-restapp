package jpa.example.jsp_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonEncoding;

import jpa.example.jsp_spring.domain.User;
import jpa.example.jsp_spring.repogitory.UserRepository;
import jpa.example.jsp_spring.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public List<User>  index() {
//    	return "redirect:/users";
    	return this.getAllUsers();
    }

    // 모든 사용자 조회
    @GetMapping
    public List<User> getAllUsers() {
//        return userRepository.findAll();
    	return userService.getAllUsers();
    	
    }

    // 사용자 생성
    @PostMapping
    public User createUser(@RequestBody User user) {
    	return userService.setUser(user);
    	
//        return userRepository.save(user);
    }

    // 이름으로 사용자 조회
    @GetMapping("/search")
    public List<User> getUsersByName(@RequestParam("name") String name) {
        return userRepository.findByName(name);
    }
}