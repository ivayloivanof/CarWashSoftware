package bg.car_wash.configurations.database;

import bg.car_wash.areas.user.entity.User;
import bg.car_wash.areas.user.entity.UserType;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class UserDefault {

	private List<User> users;

	public UserDefault() {
		this.users = new LinkedList<>();
		this.initUsers();
	}

	public void initUsers() {
		User u1 = new User();
		u1.setEmail("ivan@abv.bg");
		u1.setUsername("ivan");
		u1.setFullName("Ivan Ivanov");
		u1.setPassword("123456");
		u1.setUserType(UserType.ADMIN);
		u1.setRemuneration(new BigDecimal("1000"));
		User u2 = new User();
		u2.setEmail("pesho@abv.bg");
		u2.setUsername("pesho");
		u2.setFullName("Pesho Peshev");
		u2.setPassword("123456");
		u2.setUserType(UserType.DIRECTOR);
		u2.setRemuneration(new BigDecimal("2000"));
		User u3 = new User();
		u3.setEmail("asen@abv.bg");
		u3.setUsername("asen");
		u3.setFullName("Asen Asenov");
		u3.setPassword("123456");
		u3.setUserType(UserType.WORKER);
		u3.setRemuneration(new BigDecimal("600"));
		User u4 = new User();
		u4.setEmail("krum@abv.bg");
		u4.setUsername("krum");
		u4.setFullName("Krum Krumov");
		u4.setPassword("123456");
		u4.setUserType(UserType.PAYMASTER);
		u4.setRemuneration(new BigDecimal("800"));

		this.users.add(u1);
		this.users.add(u2);
		this.users.add(u3);
		this.users.add(u4);
	}

	public List<User> getUsers() {
		return this.users;
	}
}
