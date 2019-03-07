package edu.poly.sprg.manager.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.sprg.manager.entity.Role;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, String> {
	
	Role findByName(String name);
}
