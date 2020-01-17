package root.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import root.demo.entities.User;
import root.demo.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
	List<User> findByRole(Role role);
}
