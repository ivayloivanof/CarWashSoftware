package bg.car_wash.services;

import bg.car_wash.entities.User;
import bg.car_wash.models.bindingModels.UserLoginBindingModel;
import bg.car_wash.models.viewModels.UserSessionViewModel;

import java.util.List;

public interface UserService {

	void createUser(User userRegisterBindingModel);

	UserSessionViewModel getUserByFullNameAndPassword(UserLoginBindingModel userLoginBindingModel);

	List<User> findAllUsers();
}
