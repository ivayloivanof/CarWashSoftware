package bg.car_wash.services;

import bg.car_wash.areas.user.entity.User;
import bg.car_wash.models.bindingModels.user.UserLoginBindingModel;
import bg.car_wash.models.viewModels.user.UserSessionViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

	void createUser(User userRegisterBindingModel);

	UserSessionViewModel getUserByFullNameAndPassword(UserLoginBindingModel userLoginBindingModel);

	List<User> findAllUsers();
}
