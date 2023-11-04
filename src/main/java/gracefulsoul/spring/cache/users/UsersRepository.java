package gracefulsoul.spring.cache.users;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames = "users")
public class UsersRepository {

	private static final Map<Long, User> MAP = new LinkedHashMap<>();

	@Cacheable(key = "'all'")
	public List<User> selectAll() {
		List<User> users = MAP.values().stream().collect(Collectors.toList());
		System.out.println(users);
		return users;
	}

	@Cacheable(key = "#id", unless = "#result == null")
	public User select(Long id) {
		User user = MAP.get(id);
		System.out.println(user);
		return user;
	}

	@CachePut(key = "#user.id")
	@CacheEvict(key = "'all'")
	public User update(User user) {
		MAP.put(user.getId(), user);
		System.out.println(user);
		return user;
	}

	@Cacheable(key = "#user.id")
	@CacheEvict(key = "'all'")
	public boolean insert(User user) {
		MAP.put(user.getId(), user);
		System.out.println(user);
		return true;
	}

	public Long getId() {
		if (MAP.isEmpty()) {
			return 1L;
		} else {
			return (Long) MAP.keySet().toArray()[MAP.size() - 1] + 1;
		}
	}

	@Caching(evict = {
		@CacheEvict(key = "#id"),
		@CacheEvict(key = "'all'")
	})
	public boolean delete(Long id) {
		User user = MAP.remove(id);
		System.out.println(user);
		return true;
	}

}
