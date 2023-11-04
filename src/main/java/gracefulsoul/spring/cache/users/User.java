package gracefulsoul.spring.cache.users;

import lombok.Value;

@Value
public class User {

	private Long id;
	private String name;
	private Integer age;
	private String address;

}
