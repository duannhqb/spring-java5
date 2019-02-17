package edu.poly.sprg.manager.service;

import edu.poly.sprg.manager.entity.Staffs;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface StaffsService {
    List<Staffs> findAll();

    List<Staffs> findAll(Sort sort);

    List<Staffs> findAllById(Iterable<Integer> integers);

    <S extends Staffs> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends Staffs> S saveAndFlush(S entity);

    void deleteInBatch(Iterable<Staffs> entities);

    void deleteAllInBatch();

    Staffs getOne(Integer integer);

    <S extends Staffs> List<S> findAll(Example<S> example);

    <S extends Staffs> List<S> findAll(Example<S> example, Sort sort);

    Page<Staffs> findAll(Pageable pageable);

    <S extends Staffs> S save(S entity);

    Optional<Staffs> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Staffs entity);

    void deleteAll(Iterable<? extends Staffs> entities);

    void deleteAll();

    <S extends Staffs> Optional<S> findOne(Example<S> example);

    <S extends Staffs> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Staffs> long count(Example<S> example);

    <S extends Staffs> boolean exists(Example<S> example);
}
