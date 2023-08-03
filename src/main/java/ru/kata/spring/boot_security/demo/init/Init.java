package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Component
public class Init {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public Init(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        Role role = new Role("ROLE_USER");
        roleRepository.save(role);

        Role role1 = new Role("ROLE_ADMIN");
        roleRepository.save(role1);

        User user = new User();
        user.setUsername("user");
        user.setName("Billy");
        user.setSurname("Milligan");
        user.setPassword("user");
        user.setRoles(Collections.singletonList(role));
        userService.addNewUser(user);

        User user1 = new User();
        user1.setUsername("admin");
        user1.setName("Johnny");
        user1.setSurname("Fishman");
        user1.setPassword("admin");
        user1.setRoles(Collections.singletonList(role1));
        userService.addNewUser(user1);

        User user2 = new User();
        user2.setUsername("usertest");
        user2.setName("Kim");
        user2.setSurname("Bessinger");
        user2.setPassword("usertest");
        user2.setRoles(List.of(role, role1));
        userService.addNewUser(user2);
    }
}
