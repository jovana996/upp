package root.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import root.demo.entities.Magazine;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long> {

}
