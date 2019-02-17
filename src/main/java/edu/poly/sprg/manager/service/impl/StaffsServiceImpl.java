package edu.poly.sprg.manager.service.impl;

import edu.poly.sprg.manager.entity.Staffs;
import edu.poly.sprg.manager.repositories.StaffsRepository;
import edu.poly.sprg.manager.service.StaffsService;
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
public class StaffsServiceImpl implements StaffsService {

    @Autowired
    private StaffsRepository staffsRepository;

    @Override
    public List<Staffs> findAll() {
        return staffsRepository.findAll();
    }

    @Override
    public List<Staffs> findAll(Sort sort) {
        return staffsRepository.findAll(sort);
    }

    @Override
    public List<Staffs> findAllById(Iterable<Integer> integers) {
        return staffsRepository.findAllById(integers);
    }

    @Override
    public <S extends Staffs> List<S> saveAll(Iterable<S> entities) {
        return staffsRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        staffsRepository.flush();
    }

    @Override
    public <S extends Staffs> S saveAndFlush(S entity) {
        return staffsRepository.saveAndFlush(entity);
    }

    @Override
    public void deleteInBatch(Iterable<Staffs> entities) {
        staffsRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch() {
        staffsRepository.deleteAllInBatch();
    }

    @Override
    public Staffs getOne(Integer integer) {
        return staffsRepository.getOne(integer);
    }

    @Override
    public <S extends Staffs> List<S> findAll(Example<S> example) {
        return staffsRepository.findAll(example);
    }

    @Override
    public <S extends Staffs> List<S> findAll(Example<S> example, Sort sort) {
        return staffsRepository.findAll(example, sort);
    }

    @Override
    public Page<Staffs> findAll(Pageable pageable) {
        return staffsRepository.findAll(pageable);
    }

    @Override
    public <S extends Staffs> S save(S entity) {
        return staffsRepository.save(entity);
    }

    @Override
    public Optional<Staffs> findById(Integer integer) {
        return staffsRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return staffsRepository.existsById(integer);
    }

    @Override
    public long count() {
        return staffsRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        staffsRepository.deleteById(integer);
    }

    @Override
    public void delete(Staffs entity) {
        staffsRepository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Staffs> entities) {
        staffsRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        staffsRepository.deleteAll();
    }

    @Override
    public <S extends Staffs> Optional<S> findOne(Example<S> example) {
        return staffsRepository.findOne(example);
    }

    @Override
    public <S extends Staffs> Page<S> findAll(Example<S> example, Pageable pageable) {
        return staffsRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Staffs> long count(Example<S> example) {
        return staffsRepository.count(example);
    }

    @Override
    public <S extends Staffs> boolean exists(Example<S> example) {
        return staffsRepository.exists(example);
    }
}
