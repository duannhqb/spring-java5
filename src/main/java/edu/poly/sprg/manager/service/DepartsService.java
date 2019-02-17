package edu.poly.sprg.manager.service;

import edu.poly.sprg.manager.entity.Departs;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface DepartsService {
    List<Departs> findAll();

    List<Departs> findAll(Sort sort);

    List<Departs> findAllById(Iterable<Integer> integers);

    <S extends Departs> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends Departs> S saveAndFlush(S entity);

    void deleteInBatch(Iterable<Departs> entities);

    void deleteAllInBatch();

    Departs getOne(Integer integer);

    <S extends Departs> List<S> findAll(Example<S> example);

    <S extends Departs> List<S> findAll(Example<S> example, Sort sort);

    Page<Departs> findAll(Pageable pageable);

    <S extends Departs> S save(S entity);

    Optional<Departs> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Departs entity);

    void deleteAll(Iterable<? extends Departs> entities);

    void deleteAll();

    <S extends Departs> Optional<S> findOne(Example<S> example);

    <S extends Departs> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Departs> long count(Example<S> example);

    <S extends Departs> boolean exists(Example<S> example);
}
