package bg.car_wash.areas.role.service;

import bg.car_wash.areas.role.entity.Role;

import java.util.List;

public interface RoleService {
	void createRole(Role role);

	List<Role> findAllRole();
}
