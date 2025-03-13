package jpa.example.jsp_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpa.example.jsp_spring.domain.User;
import jpa.example.jsp_spring.repogitory.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	

	/**
	 * 
	 * @return
	 */
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
		
	}




	/**
	 * 
	 * @param user
	 * @return
	 */
	public User setUser(User user) {
		return userRepository.save(user);
	}

}
