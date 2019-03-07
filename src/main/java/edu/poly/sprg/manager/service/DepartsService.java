package edu.poly.sprg.manager.service;

import edu.poly.sprg.manager.entity.Departs;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface DepartsService {

	<S extends Departs> boolean exists(Example<S> example);

	<S extends Departs> long count(Example<S> example);

	<S extends Departs> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Departs> Optional<S> findOne(Example<S> example);

	void deleteAll();

	void deleteAll(Iterable<? extends Departs> entities);

	void delete(Departs entity);

	void deleteById(Integer integer);

	long count();

	boolean existsById(Integer integer);

	Optional<Departs> findById(Integer integer);

	<S extends Departs> S save(S entity);

	Page<Departs> findAll(Pageable pageable);

	<S extends Departs> List<S> findAll(Example<S> example, Sort sort);

	<S extends Departs> List<S> findAll(Example<S> example);

	Departs getOne(Integer integer);

	void deleteAllInBatch();

	void deleteInBatch(Iterable<Departs> entities);

	<S extends Departs> S saveAndFlush(S entity);

	void flush();

	<S extends Departs> List<S> saveAll(Iterable<S> entities);

	List<Departs> findAllById(Iterable<Integer> integers);

	List<Departs> findAll(Sort sort);

	List<Object[]> getPoint();

	List<Departs> findAll();
}
