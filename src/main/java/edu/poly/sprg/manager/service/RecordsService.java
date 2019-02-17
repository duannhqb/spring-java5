package edu.poly.sprg.manager.service;

import edu.poly.sprg.manager.entity.Records;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface RecordsService {
    List<Records> findAll();

    List<Records> findAll(Sort sort);

    List<Records> findAllById(Iterable<Integer> integers);

    <S extends Records> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends Records> S saveAndFlush(S entity);

    void deleteInBatch(Iterable<Records> entities);

    void deleteAllInBatch();

    Records getOne(Integer integer);

    <S extends Records> List<S> findAll(Example<S> example);

    <S extends Records> List<S> findAll(Example<S> example, Sort sort);

    Page<Records> findAll(Pageable pageable);

    <S extends Records> S save(S entity);

    Optional<Records> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Records entity);

    void deleteAll(Iterable<? extends Records> entities);

    void deleteAll();

    <S extends Records> Optional<S> findOne(Example<S> example);

    <S extends Records> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Records> long count(Example<S> example);

    <S extends Records> boolean exists(Example<S> example);
}
