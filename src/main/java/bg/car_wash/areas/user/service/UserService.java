package bg.car_wash.areas.user.service;

import bg.car_wash.areas.user.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

	void createUser(User userRegisterBindingModel);

	List<User> findAllUsers();
}
