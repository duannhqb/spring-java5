package edu.poly.sprg.manager.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.sprg.manager.entity.Users;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, String> {
	
	Users findByUsername(String username);
}
