package gracefulsoul.spring.cache.users;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

	private final UsersService usersService;

	@GetMapping
	public List<User> selectAll() {
		return this.usersService.selectAll();
	}

	@GetMapping("/{id}")
	public User select(@PathVariable("id") Long id) {
		return this.usersService.select(id);
	}

	@PutMapping
	public User update(@RequestBody User user) {
		return this.usersService.update(user);
	}

	@PostMapping
	public boolean insert(@RequestBody User user) {
		return this.usersService.insert(user);
	}

	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable("id") Long id) {
		return this.usersService.delete(id);
	}

}
