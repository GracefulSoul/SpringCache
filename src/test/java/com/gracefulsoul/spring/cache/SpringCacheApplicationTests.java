package com.gracefulsoul.spring.cache;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import gracefulsoul.spring.cache.users.User;
import gracefulsoul.spring.cache.users.UsersRepository;
import gracefulsoul.spring.cache.users.UsersService;

@SpringBootTest(classes = { UsersService.class, UsersRepository.class })
@TestMethodOrder(OrderAnnotation.class)
class SpringCacheApplicationTests {

	@Autowired
	private UsersService usersService;

	@Test
	@Order(value = 0)
	void insert() {
		assertTrue(this.usersService.insert(new User(null, "Grace", 20, "Seoul, Republic of Korea")));
	}

	@Test
	@Order(value = 1)
	void select() {
		User user = this.usersService.select(1L);
		assertSame(user.getName(), "Grace");
		assertSame(user.getAge(), 20);
		assertSame(user.getAddress(), "Seoul, Republic of Korea");
	}

	@Test
	@Order(value = 2)
	void update() {
		User user = this.usersService.update(new User(1L, "Grace Kim", 30, "Seoul, Republic of Korea"));
		assertNotSame(user.getAge(), 20);
	}

	@Test
	@Order(value = 3)
	void delete() {
		assertTrue(this.usersService.delete(1L));
	}

	@Test
	@Order(value = 4)
	void selectAll() {
		assertTrue(this.usersService.selectAll().isEmpty());
	}

}
