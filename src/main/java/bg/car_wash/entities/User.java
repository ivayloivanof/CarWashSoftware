package bg.car_wash.entities;

import bg.car_wash.entities.enumerations.UserType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "full_name", nullable = false)
	@Size(min = 7, max = 65)
	private String fullName;

	@Column(name = "email", nullable = true)
	@Size(min = 7, max = 100)
	private String email;

	@Column(name = "password", nullable = false)
	@Size(min = 4, max = 15)
	private String password;

	@Basic
	private BigDecimal remuneration;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_type")
	private UserType userType;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getRemuneration() {
		return remuneration;
	}

	public void setRemuneration(BigDecimal remuneration) {
		this.remuneration = remuneration;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
