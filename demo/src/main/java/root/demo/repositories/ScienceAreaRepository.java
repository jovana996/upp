package root.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import root.demo.entities.ScienceArea;

@Repository
public interface ScienceAreaRepository extends JpaRepository<ScienceArea, Long> {

}
