package edu.poly.sprg.manager.service;

import edu.poly.sprg.manager.entity.Staffs;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface StaffsService {

	<S extends Staffs> boolean exists(Example<S> example);

	<S extends Staffs> long count(Example<S> example);

	<S extends Staffs> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Staffs> Optional<S> findOne(Example<S> example);

	void deleteAll();

	void deleteAll(Iterable<? extends Staffs> entities);

	void delete(Staffs entity);

	void deleteById(Integer integer);

	long count();

	boolean existsById(Integer integer);

	Optional<Staffs> findById(Integer integer);

	<S extends Staffs> S save(S entity);

	Page<Staffs> findAll(Pageable pageable);

	<S extends Staffs> List<S> findAll(Example<S> example, Sort sort);

	<S extends Staffs> List<S> findAll(Example<S> example);

	Staffs getOne(Integer integer);

	void deleteAllInBatch();

	void deleteInBatch(Iterable<Staffs> entities);

	<S extends Staffs> S saveAndFlush(S entity);

	void flush();

	<S extends Staffs> List<S> saveAll(Iterable<S> entities);

	List<Staffs> findAllById(Iterable<Integer> integers);

	List<Staffs> findAll(Sort sort);

	List<Staffs> findAll();

	List<String> seachStaff(String input);

	List<Object[]> getPoint();
	
	List<Object[]> getStaff();
}
