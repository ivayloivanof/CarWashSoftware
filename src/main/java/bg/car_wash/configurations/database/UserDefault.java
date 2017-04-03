package bg.car_wash.configurations.database;

import bg.car_wash.areas.user.entity.User;
import bg.car_wash.areas.user.entity.UserType;
import bg.car_wash.configurations.user.UserConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class UserDefault {

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private String password;

	private List<User> users;

	public UserDefault() {
		this.users = new LinkedList<>();
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
		this.password = this.bCryptPasswordEncoder.encode("123456");
		this.initUsers();
	}

	public void initUsers() {
		User u1 = new User();
		u1.setEmail("ivan@abv.bg");
		u1.setFullName("Ivan Ivanov");
		u1.setPassword(this.password);
		u1.setUserType(UserType.ADMIN);
		u1.setRemuneration(new BigDecimal(UserConfiguration.ADMIN_REMUNERATION));
		u1.setAccountNonExpired(true);
		u1.setAccountNonLocked(true);
		u1.setCredentialsNonExpired(true);
		u1.setEnabled(true);

		User u2 = new User();
		u2.setEmail("pesho@abv.bg");
		u2.setFullName("Pesho Peshev");
		u2.setPassword(this.password);
		u2.setUserType(UserType.DIRECTOR);
		u2.setRemuneration(new BigDecimal(UserConfiguration.DIRECTOR_REMUNERATION));
		u2.setAccountNonExpired(true);
		u2.setAccountNonLocked(true);
		u2.setCredentialsNonExpired(true);
		u2.setEnabled(true);

		User u3 = new User();
		u3.setEmail("asen@abv.bg");
		u3.setFullName("Asen Asenov");
		u3.setPassword(this.password);
		u3.setUserType(UserType.WORKER);
		u3.setRemuneration(new BigDecimal(UserConfiguration.WORKER_REMUNERATION));
		u3.setAccountNonExpired(true);
		u3.setAccountNonLocked(true);
		u3.setCredentialsNonExpired(true);
		u3.setEnabled(true);

		User u4 = new User();
		u4.setEmail("krum@abv.bg");
		u4.setFullName("Krum Krumov");
		u4.setPassword(this.password);
		u4.setUserType(UserType.PAYMASTER);
		u4.setRemuneration(new BigDecimal(UserConfiguration.PAYMASTER_REMUNERATION));
		u4.setAccountNonExpired(true);
		u4.setAccountNonLocked(true);
		u4.setCredentialsNonExpired(true);
		u4.setEnabled(true);

		this.users.add(u1);
		this.users.add(u2);
		this.users.add(u3);
		this.users.add(u4);
	}

	public List<User> getUsers() {
		return this.users;
	}
}
