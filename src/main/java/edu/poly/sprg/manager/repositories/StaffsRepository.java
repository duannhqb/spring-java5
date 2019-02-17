package edu.poly.sprg.manager.repositories;

import edu.poly.sprg.manager.entity.Staffs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StaffsRepository extends JpaRepository<Staffs, Integer> {
}
