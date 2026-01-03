package jpa.example.jsp_spring;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import jpa.example.jsp_spring.domain.User;
import jpa.example.jsp_spring.service.UserService;

//@WebMvcTest(UserController.class)
@SpringBootTest
// @AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

//	@Autowired
//	@MockBean
//	private UserRepository userRepository;

	@Autowired
//	@MockBean
	private UserService userService;

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private User user;
//
//    @BeforeEach
//    public void setUp() {
//        user = new User("John Doe", "john.doe@example.com");
//    }
//
//    @Test
//    public void testCreateUser() throws Exception {
//        mockMvc.perform(post("/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(user)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@example.com"));
//    }
//
//    @Test
//    public void testGetAllUsers() throws Exception {
//        mockMvc.perform(get("/users"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Alice"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("alice@example.com"));
//    }
//
//    @Test
//    public void testGetUsersByName() throws Exception {
//        mockMvc.perform(get("/users/search")
//                .param("name", "Alice"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Alice"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("alice@example.com"));
//    }

	@Autowired
	private MockMvc mockMvc;

//	@MockBean
//	private UserRepository userRepository;

	@Autowired
	private ObjectMapper objectMapper;

	private User user;

	@BeforeEach
	public void setUp() {
//		 mockMvc = MockMvcBuilders.standaloneSetup(codeController).build();
		user = new User("John Doe", "john.doe@example.com");
	}

	@Test
	public void testCreateUser() throws Exception {
		mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
//				.content(convertObjectToJsonString(muffin))
//				.contentType(MediaType.APPLICATION_JSON)

				.content(objectMapper.writeValueAsString(user)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@example.com"));
	}

	@Test
	public void testGetAllUsers() throws Exception {

		mockMvc.perform(get("/users"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Alice"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("alice@example.com"))
//				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John Doe"))
//				.andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("john.doe@example.com"))

		;

//		.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Alice"))
//		.andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("alice@example.com"));

//		when(userService.getAllUsers()).thenReturn(List.of(new User("Alice", "alice@example.com")));
		when(userService.getAllUsers()).thenReturn(List.of(new User("John Doe", "john.doe@example.com")));
	}

	@Test
	public void testGetUsersByName() throws Exception {
//		mockMvc.perform(get("/users/search").param("name", "Alice")).andExpect(MockMvcResultMatchers.status().isOk())
		mockMvc.perform(get("/users/search").param("name", "John Doe"))
		.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print())
//				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Alice"))
//				.andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("alice@example.com"));
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John Doe"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("john.doe@example.com"));
	}

}