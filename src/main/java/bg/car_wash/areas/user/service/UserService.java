package bg.car_wash.areas.user.service;

import bg.car_wash.areas.user.entity.User;
import bg.car_wash.areas.user.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

	void createUser(User userRegisterBindingModel);

	List<User> findAllUsers();

	User findUserById(Long id) throws UserNotFoundException;

	void updateUser(User user);

	void deleteUserById(Long id);
}
