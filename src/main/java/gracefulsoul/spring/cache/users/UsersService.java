package gracefulsoul.spring.cache.users;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {

	private final UsersRepository usersRepository;

	public List<User> selectAll() {
		return this.usersRepository.selectAll();
	}

	public User select(Long id) {
		return this.usersRepository.select(id);
	}

	public User update(User user) {
		return this.usersRepository.update(user);
	}

	public boolean insert(User user) {
		User newUser = new User(this.usersRepository.getId(), user.getName(), user.getAge(), user.getAddress());
		return this.usersRepository.insert(newUser);
	}

	public boolean delete(Long id) {
		return this.usersRepository.delete(id);
	}

}
