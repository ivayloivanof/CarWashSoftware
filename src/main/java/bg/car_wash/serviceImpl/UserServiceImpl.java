package bg.car_wash.serviceImpl;

import bg.car_wash.entities.User;
import bg.car_wash.models.bindingModels.UserLoginBindingModel;
import bg.car_wash.models.viewModels.UserSessionViewModel;
import bg.car_wash.repositories.UserRepository;
import bg.car_wash.services.UserService;
import bg.car_wash.utils.parser.interfaces.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelParser modelParser;

	@Override
	public void createUser(User user) {
		this.userRepository.save(user);
	}

	@Override
	public UserSessionViewModel getUserByFullNameAndPassword(UserLoginBindingModel userLoginBindingModel) {
		User user = this.modelParser.convert(userLoginBindingModel, User.class);
		User loggedUser = this.userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword());

		return this.modelParser.convert(loggedUser, UserSessionViewModel.class);
	}

	@Override
	public List<User> findAllUsers() {
		List<User> users = this.userRepository.findAll();
		return users;
	}
}
