package bg.car_wash.areas.user.entity;

import bg.car_wash.areas.role.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

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
	@Size(min = 6, max = 80)
	private String password;

	@Basic
	private BigDecimal remuneration;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_type")
	private UserType userType;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles",
	joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> authorities;

	@Column(name = "is_account_non_expired")
	private boolean isAccountNonExpired;

	@Column(name = "is_account_non_locked")
	private boolean isAccountNonLocked;

	@Column(name = "is_credentials_non_expired")
	private boolean isCredentialsNonExpired;

	@Column(name = "is_enabled")
	private boolean isEnabled;

	public User() {
	}

	@Override
	public Set<Role> getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}

	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
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

	public void setAccountNonExpired(boolean accountNonExpired) {
		isAccountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		isAccountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		isCredentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}
}
