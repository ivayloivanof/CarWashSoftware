package bg.car_wash.areas.user.serviceImpl;

import bg.car_wash.areas.user.entity.User;
import bg.car_wash.areas.user.repository.UserRepository;
import bg.car_wash.areas.user.service.UserService;
import bg.car_wash.configurations.error.Errors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void createUser(User user) {
		this.userRepository.save(user);
	}

	@Override
	public List<User> findAllUsers() {
		List<User> users = this.userRepository.findAll();
		return users;
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = this.userRepository.findOneByEmail(email);

		if (user == null) {
//			throw  new UserNotFoundException();
			throw new UsernameNotFoundException(Errors.NOT_FOUND_USER);
		}

		return user;
	}
}
