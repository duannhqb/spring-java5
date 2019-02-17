package edu.poly.sprg.manager.service.impl;

import edu.poly.sprg.manager.entity.Records;
import edu.poly.sprg.manager.repositories.RecordsRepository;
import edu.poly.sprg.manager.service.RecordsService;
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
public class RecordsServiceImpl implements RecordsService {

    @Autowired
    private RecordsRepository recordsRepository;

    @Override
    public List<Records> findAll() {
        return recordsRepository.findAll();
    }

    @Override
    public List<Records> findAll(Sort sort) {
        return recordsRepository.findAll(sort);
    }

    @Override
    public List<Records> findAllById(Iterable<Integer> integers) {
        return recordsRepository.findAllById(integers);
    }

    @Override
    public <S extends Records> List<S> saveAll(Iterable<S> entities) {
        return recordsRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        recordsRepository.flush();
    }

    @Override
    public <S extends Records> S saveAndFlush(S entity) {
        return recordsRepository.saveAndFlush(entity);
    }

    @Override
    public void deleteInBatch(Iterable<Records> entities) {
        recordsRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch() {
        recordsRepository.deleteAllInBatch();
    }

    @Override
    public Records getOne(Integer integer) {
        return recordsRepository.getOne(integer);
    }

    @Override
    public <S extends Records> List<S> findAll(Example<S> example) {
        return recordsRepository.findAll(example);
    }

    @Override
    public <S extends Records> List<S> findAll(Example<S> example, Sort sort) {
        return recordsRepository.findAll(example, sort);
    }

    @Override
    public Page<Records> findAll(Pageable pageable) {
        return recordsRepository.findAll(pageable);
    }

    @Override
    public <S extends Records> S save(S entity) {
        return recordsRepository.save(entity);
    }

    @Override
    public Optional<Records> findById(Integer integer) {
        return recordsRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return recordsRepository.existsById(integer);
    }

    @Override
    public long count() {
        return recordsRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        recordsRepository.deleteById(integer);
    }

    @Override
    public void delete(Records entity) {
        recordsRepository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Records> entities) {
        recordsRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        recordsRepository.deleteAll();
    }

    @Override
    public <S extends Records> Optional<S> findOne(Example<S> example) {
        return recordsRepository.findOne(example);
    }

    @Override
    public <S extends Records> Page<S> findAll(Example<S> example, Pageable pageable) {
        return recordsRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Records> long count(Example<S> example) {
        return recordsRepository.count(example);
    }

    @Override
    public <S extends Records> boolean exists(Example<S> example) {
        return recordsRepository.exists(example);
    }
}
