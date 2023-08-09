package ru.kata.spring.boot_security.demo.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;


import java.util.HashSet;
import java.util.Set;


@Component
public class Init implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Init(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(String... arg) throws Exception {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        Set<Role> adminRoles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);
        adminRoles.add(roleAdmin);
        userRoles.add(roleUser);

        User userAdmin = new User("Roy", "Jones", 45, "admin", passwordEncoder.encode("admin"), adminRoles);
        User userUser = new User("Sam", "Mendez", 57, "user", passwordEncoder.encode("user"), userRoles);
        userRepository.save(userAdmin);
        userRepository.save(userUser);
    }
}
