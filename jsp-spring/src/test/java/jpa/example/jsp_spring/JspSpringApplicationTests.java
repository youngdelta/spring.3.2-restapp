package jpa.example.jsp_spring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jpa.example.jsp_spring.repogitory.UserRepository;
import jpa.example.jsp_spring.service.UserService;

@SpringBootTest
class JspSpringApplicationTests {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

    @Test
    public void contextLoads() {
    	
    	assertThat(userService).isNotNull();
        assertThat(userRepository).isNotNull();
    }

}
