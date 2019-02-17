package edu.poly.sprg.manager.service.impl;

import edu.poly.sprg.manager.entity.Departs;
import edu.poly.sprg.manager.repositories.DepartRepository;
import edu.poly.sprg.manager.service.DepartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartsServiceImpl implements DepartsService {

    @Autowired
    private DepartRepository departRepository;

    @Override
    public List<Departs> findAll() {
        return departRepository.findAll();
    }

    @Override
    public List<Departs> findAll(Sort sort) {
        return departRepository.findAll(sort);
    }

    @Override
    public List<Departs> findAllById(Iterable<Integer> integers) {
        return departRepository.findAllById(integers);
    }

    @Override
    public <S extends Departs> List<S> saveAll(Iterable<S> entities) {
        return departRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        departRepository.flush();
    }

    @Override
    public <S extends Departs> S saveAndFlush(S entity) {
        return departRepository.saveAndFlush(entity);
    }

    @Override
    public void deleteInBatch(Iterable<Departs> entities) {
        departRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch() {
        departRepository.deleteAllInBatch();
    }

    @Override
    public Departs getOne(Integer integer) {
        return departRepository.getOne(integer);
    }

    @Override
    public <S extends Departs> List<S> findAll(Example<S> example) {
        return departRepository.findAll(example);
    }

    @Override
    public <S extends Departs> List<S> findAll(Example<S> example, Sort sort) {
        return departRepository.findAll(example, sort);
    }

    @Override
    public Page<Departs> findAll(Pageable pageable) {
        return departRepository.findAll(pageable);
    }

    @Override
    public <S extends Departs> S save(S entity) {
        return departRepository.save(entity);
    }

    @Override
    public Optional<Departs> findById(Integer integer) {
        return departRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return departRepository.existsById(integer);
    }

    @Override
    public long count() {
        return departRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        departRepository.deleteById(integer);
    }

    @Override
    public void delete(Departs entity) {
        departRepository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Departs> entities) {
        departRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        departRepository.deleteAll();
    }

    @Override
    public <S extends Departs> Optional<S> findOne(Example<S> example) {
        return departRepository.findOne(example);
    }

    @Override
    public <S extends Departs> Page<S> findAll(Example<S> example, Pageable pageable) {
        return departRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Departs> long count(Example<S> example) {
        return departRepository.count(example);
    }

    @Override
    public <S extends Departs> boolean exists(Example<S> example) {
        return departRepository.exists(example);
    }
}
