package bg.car_wash.areas.user.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "full_name", nullable = false)
	@Size(min = 7, max = 65)
	private String fullName;

	@Column(name = "username", nullable = false)
	@Size(min = 3, max = 25)
	private String username;

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
