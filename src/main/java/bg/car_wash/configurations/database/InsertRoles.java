package bg.car_wash.configurations.database;

import bg.car_wash.areas.role.entity.Role;
import bg.car_wash.areas.user.entity.UserType;

import java.util.LinkedList;
import java.util.List;

public class InsertRoles {

	private List<Role> roles;

	public InsertRoles() {
		this.roles = new LinkedList<>();
	}

	public List<Role> getRoles() {
		Role admin = new Role();
		admin.setAuthority(UserType.ADMIN.toString());
		this.roles.add(admin);
		Role director = new Role();
		director.setAuthority(UserType.DIRECTOR.toString());
		this.roles.add(director);
		Role worker = new Role();
		worker.setAuthority(UserType.WORKER.toString());
		this.roles.add(worker);
		Role paymaster = new Role();
		paymaster.setAuthority(UserType.PAYMASTER.toString());
		this.roles.add(paymaster);
		Role washer = new Role();
		washer.setAuthority(UserType.WASHER.toString());
		this.roles.add(washer);
		Role launderer = new Role();
		launderer.setAuthority(UserType.LAUNDERER.toString());
		this.roles.add(launderer);

		return this.roles;
	}
}
