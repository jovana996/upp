package root.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import root.demo.entities.Paper;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Long>  {

}
