package root.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entities.Magazine;
import root.demo.entities.Subscription;
import root.demo.entities.User;
import root.demo.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {

	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Autowired
	UserService userService;

	@Autowired
	MagazineService magazineService;

	public boolean isPaidSubscription(Long userId, Long magazineId) {
		User user = userService.findById(userId);
		Magazine magazine = magazineService.getById(magazineId);
		List<Subscription> subs = subscriptionRepository.findByMagazineAndUser(magazine, user);
		if (subs != null && subs.size() > 0) {
			for (Subscription sub : subs) {
				if (sub.getPaid() && !sub.getCanceled()) {
					return true;
				}
			}
		}
		return false;
	}

}
