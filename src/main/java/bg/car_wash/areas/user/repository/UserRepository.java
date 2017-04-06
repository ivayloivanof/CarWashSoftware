package bg.car_wash.areas.user.repository;

import bg.car_wash.areas.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//	@Query("SELECT u FROM User AS u WHERE u.email = :email AND u.password = :password")
//	User findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	List<User> findAll();

	User findOneByEmail(String email);
}
