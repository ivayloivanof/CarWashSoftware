package bg.car_wash.areas.user.services;

import bg.car_wash.areas.user.entity.User;
import bg.car_wash.areas.user.models.bindingModels.UserLoginBindingModel;
import bg.car_wash.areas.user.models.viewModels.UserSessionViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

	void createUser(User userRegisterBindingModel);

	List<User> findAllUsers();
}
