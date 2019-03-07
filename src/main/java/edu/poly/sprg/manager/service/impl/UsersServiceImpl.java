package edu.poly.sprg.manager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.poly.sprg.manager.entity.Role;
import edu.poly.sprg.manager.entity.Users;
import edu.poly.sprg.manager.repositories.UsersRepository;
import edu.poly.sprg.manager.service.UsersService;

@Service
@Transactional
public class UsersServiceImpl implements UsersService, UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public List<Users> findAll() {
		return usersRepository.findAll();
	}

	@Override
	public List<Users> findAll(Sort sort) {
		return usersRepository.findAll(sort);
	}

	@Override
	public List<Users> findAllById(Iterable<String> strings) {
		return usersRepository.findAllById(strings);
	}

	@Override
	public <S extends Users> List<S> saveAll(Iterable<S> entities) {
		return usersRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		usersRepository.flush();
	}

	@Override
	public <S extends Users> S saveAndFlush(S entity) {
		return usersRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<Users> entities) {
		usersRepository.deleteInBatch(entities);
	}

	@Override
	public void deleteAllInBatch() {
		usersRepository.deleteAllInBatch();
	}

	@Override
	public Users getOne(String s) {
		return usersRepository.getOne(s);
	}

	@Override
	public <S extends Users> List<S> findAll(Example<S> example) {
		return usersRepository.findAll(example);
	}

	@Override
	public <S extends Users> List<S> findAll(Example<S> example, Sort sort) {
		return usersRepository.findAll(example, sort);
	}

	@Override
	public Page<Users> findAll(Pageable pageable) {
		return usersRepository.findAll(pageable);
	}

	@Override
	public <S extends Users> S save(S entity) {
		return usersRepository.save(entity);
	}

	@Override
	public Optional<Users> findById(String s) {
		return usersRepository.findById(s);
	}

	@Override
	public boolean existsById(String s) {
		return usersRepository.existsById(s);
	}

	@Override
	public long count() {
		return usersRepository.count();
	}

	@Override
	public void deleteById(String s) {
		usersRepository.deleteById(s);
	}

	@Override
	public void delete(Users entity) {
		usersRepository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends Users> entities) {
		usersRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		usersRepository.deleteAll();
	}

	@Override
	public <S extends Users> Optional<S> findOne(Example<S> example) {
		return usersRepository.findOne(example);
	}

	@Override
	public <S extends Users> Page<S> findAll(Example<S> example, Pageable pageable) {
		return usersRepository.findAll(example, pageable);
	}

	@Override
	public <S extends Users> long count(Example<S> example) {
		return usersRepository.count(example);
	}

	@Override
	public <S extends Users> boolean exists(Example<S> example) {
		return usersRepository.exists(example);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		List<Role> roles = user.getRoles();
		for (Role role : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities);
	}

	@Override
	public UserDetails findByUsername(String username) {
		Users user = usersRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		List<Role> roles = user.getRoles();
		for (Role role : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities);
	}

}
