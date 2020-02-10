package root.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import root.demo.entities.Magazine;
import root.demo.entities.Subscription;
import root.demo.entities.User;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	
	List<Subscription> findByMagazineAndUser(Magazine magazine, User user);
}
