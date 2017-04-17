package bg.car_wash.configurations.database;

import bg.car_wash.areas.user.entity.User;
import bg.car_wash.areas.user.entity.UserType;
import bg.car_wash.configurations.user.UserConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class InsertUsers {

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private String password;

	private List<User> users;

	public InsertUsers() {
		this.users = new LinkedList<>();
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
		this.password = this.bCryptPasswordEncoder.encode("123456");
		this.initUsers();
	}

	public void initUsers() {
		User u1 = new User();
		u1.setEmail("ivanof@abv.bg");
		u1.setFullName("Ivaylo Ivanov");
		u1.setPassword(this.password);
		u1.setUserType(UserType.ADMIN);
		u1.setRemuneration(new BigDecimal(UserConfiguration.ADMIN_REMUNERATION));

		User u2 = new User();
		u2.setEmail("pesho@abv.bg");
		u2.setFullName("Petar Petrov");
		u2.setPassword(this.password);
		u2.setUserType(UserType.DIRECTOR);
		u2.setRemuneration(new BigDecimal(UserConfiguration.DIRECTOR_REMUNERATION));

		User u3 = new User();
		u3.setEmail("asen@abv.bg");
		u3.setFullName("Asen Asenov");
		u3.setPassword(this.password);
		u3.setUserType(UserType.WORKER);
		u3.setRemuneration(new BigDecimal(UserConfiguration.WORKER_REMUNERATION));

		User u4 = new User();
		u4.setEmail("krum@abv.bg");
		u4.setFullName("Krum Krumov");
		u4.setPassword(this.password);
		u4.setUserType(UserType.PAYMASTER);
		u4.setRemuneration(new BigDecimal(UserConfiguration.PAYMASTER_REMUNERATION));

		User u5 = new User();
		u5.setEmail("asen@abv.bg");
		u5.setFullName("Asen Asenov");
		u5.setPassword(this.password);
		u5.setUserType(UserType.WORKER);
		u5.setRemuneration(new BigDecimal(UserConfiguration.WASHER_REMUNERATION));

		User u6 = new User();
		u6.setEmail("pavel@abv.bg");
		u6.setFullName("Pavel Pavlov");
		u6.setPassword(this.password);
		u6.setUserType(UserType.WORKER);
		u6.setRemuneration(new BigDecimal(UserConfiguration.LAUNDERER_REMUNERATION));

		this.users.add(u1);
		this.users.add(u2);
		this.users.add(u3);
		this.users.add(u4);
		this.users.add(u5);
		this.users.add(u6);

		for (User user : this.getUsers()) {
			user.setAccountNonExpired(true);
			user.setAccountNonLocked(true);
			user.setCredentialsNonExpired(true);
			user.setEnabled(true);
		}
	}

	public List<User> getUsers() {
		return this.users;
	}
}
