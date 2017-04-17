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

	private UserRepository userRepository;
	private ModelMapper modelMapper;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void createUser(User user) {
		this.userRepository.save(user);
	}

	@Override
	public List<User> findAllUsers() {
		List<User> users = this.userRepository.findAll();
		return users;
	}

	@Override
	public User findUserById(Long id) {
		User user = this.userRepository.findOne(id);
		return user;
	}

	@Override
	public void updateUser(User user) {
		this.userRepository.save(user);
	}

	@Override
	public void deleteUserById(Long id) {
		this.userRepository.delete(id);
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = this.userRepository.findOneByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException(Errors.NOT_FOUND_USER);
		}

		return user;
	}
}
