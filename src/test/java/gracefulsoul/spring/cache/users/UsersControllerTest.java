package gracefulsoul.spring.cache.users;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UsersController.class)
@TestMethodOrder(OrderAnnotation.class)
public class UsersControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@SpyBean
	private UsersService usersService;

	@SpyBean
	private UsersRepository usersRepository;

	@Test
	@Order(value = 0)
	void insert() throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/users")
					.content("{\"name\":\"Grace\",\"address\":\"Seoul, Republic of Korea\",\"age\":22}")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$").value(true))
		.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(value = 1)
	void select() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", 1))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Grace"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.age").value(22))
		.andExpect(MockMvcResultMatchers.jsonPath("$.address").value("Seoul, Republic of Korea"));
	}

	@Test
	@Order(value = 2)
	void update() throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/users")
					.content("{\"id\":1,\"name\":\"Kim\",\"address\":\"Seoul\",\"age\":30}")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.age").value(30));
	}

	@Test
	@Order(value = 3)
	void delete() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/{id}", 1))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
	}

	@Test
	@Order(value = 4)
	void selectAll() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
	}

}
