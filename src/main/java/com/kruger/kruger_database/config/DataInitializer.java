package com.kruger.kruger_database.config;

import com.kruger.kruger_database.models.Role;
import com.kruger.kruger_database.models.UserEntity;
import com.kruger.kruger_database.repositories.RoleRepository;
import com.kruger.kruger_database.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.password.PasswordEncoder;


import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    @Transactional
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   RoleRepository roleRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByUsername("admin")) {


                UserEntity user = new UserEntity();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("password"));

                userRepository.save(user);
                System.out.println("User admin created");
            }

            UserEntity adminUser = userRepository.findByUsername("admin");
            Role role = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setName("ADMIN");
                        return roleRepository.saveAndFlush(newRole);
                    });

            Set<Role> roles = new HashSet<>();
                roles.add(role);
            adminUser.setRoles(roles);
            userRepository.save(adminUser);


        };
    }
}
