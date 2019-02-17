package edu.poly.sprg.manager.service;

import edu.poly.sprg.manager.entity.Users;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<Users> findAll();

    List<Users> findAll(Sort sort);

    List<Users> findAllById(Iterable<String> strings);

    <S extends Users> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends Users> S saveAndFlush(S entity);

    void deleteInBatch(Iterable<Users> entities);

    void deleteAllInBatch();

    Users getOne(String s);

    <S extends Users> List<S> findAll(Example<S> example);

    <S extends Users> List<S> findAll(Example<S> example, Sort sort);

    Page<Users> findAll(Pageable pageable);

    <S extends Users> S save(S entity);

    Optional<Users> findById(String s);

    boolean existsById(String s);

    long count();

    void deleteById(String s);

    void delete(Users entity);

    void deleteAll(Iterable<? extends Users> entities);

    void deleteAll();

    <S extends Users> Optional<S> findOne(Example<S> example);

    <S extends Users> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Users> long count(Example<S> example);

    <S extends Users> boolean exists(Example<S> example);
}
