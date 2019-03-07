package edu.poly.sprg.manager.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.sprg.manager.entity.Departs;

@Repository
@Transactional
public interface DepartRepository extends JpaRepository<Departs, Integer> {
	
	// thanh tich = 1
	@Query(value="select d.name, count(case when r.type = 1 then 1 end), count(case when r.type = 0 then 0 end), (count(case when r.type = 1 then 1 end) - count(case when r.type = 0 then 0 end)) from departs d join staffs s on s.departId= d.id join records r on s.id = r.staffId group by d.id", nativeQuery=true)
	List<Object[]> getPoint();
}
