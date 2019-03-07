package edu.poly.sprg.manager.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import edu.poly.sprg.manager.entity.Role;
import edu.poly.sprg.manager.entity.Users;
import edu.poly.sprg.manager.repositories.RoleRepository;
import edu.poly.sprg.manager.repositories.UsersRepository;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        // Roles
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }

        if (roleRepository.findByName("ROLE_MEMBER") == null) {
            roleRepository.save(new Role("ROLE_MEMBER"));
        }

        // Admin account
        if (userRepository.findByUsername("admin") == null) {
            Users admin = new Users();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByName("ROLE_ADMIN"));
            roles.add(roleRepository.findByName("ROLE_MEMBER"));
            admin.setRoles(roles);
            userRepository.save(admin);
        }

        // Member account
        if (userRepository.findByUsername("member") == null) {
            Users user = new Users();
            user.setUsername("member");
            user.setPassword(passwordEncoder.encode("123456"));
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByName("ROLE_MEMBER"));
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}
