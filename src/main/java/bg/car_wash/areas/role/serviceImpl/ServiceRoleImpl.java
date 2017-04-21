package bg.car_wash.areas.role.serviceImpl;

import bg.car_wash.areas.role.entity.Role;
import bg.car_wash.areas.role.repository.RoleRepository;
import bg.car_wash.areas.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRoleImpl implements RoleService {

	private RoleRepository roleRepository;

	@Autowired
	public ServiceRoleImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public void createRole(Role role) {
		this.roleRepository.save(role);
	}

	@Override
	public List<Role> findAllRole() {
		return this.roleRepository.findAll();
	}
}
