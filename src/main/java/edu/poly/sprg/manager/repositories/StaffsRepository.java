package edu.poly.sprg.manager.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.sprg.manager.entity.Staffs;

@Repository
@Transactional
public interface StaffsRepository extends JpaRepository<Staffs, Integer> {

	// thanh tich = 1
	@Query(value = "select s.name, count(case when r.type = 1 then 1 end), count(case when r.type = 0 then 0 end), (count(case when r.type = 1 then 1 end) - count(case when r.type = 0 then 0 end)) from staffs s join records r on s.id = r.staffId group by s.id", nativeQuery = true)
	List<Object[]> getPoint();

	@Query(value = "select s.name as 'employee name', s.photo, d.name as 'depart name' from staffs s join departs d on s.departId = d.id join records r on r.staffId = s.id where r.type = 1 group by s.id order by count(r.type) desc limit 10", nativeQuery = true)
	List<Object[]> getStaff();

	@Query(value = "SELECT * FROM staffs WHERE name like %:value%", nativeQuery = true)
	List<Staffs> seachStaff(String value);

}
