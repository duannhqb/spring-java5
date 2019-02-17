package edu.poly.sprg.manager.repositories;

import edu.poly.sprg.manager.entity.Departs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DepartRepository extends JpaRepository<Departs, Integer> {
}
