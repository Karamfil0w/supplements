package com.example.supplements.seeders;

import com.example.supplements.model.entities.User;
import com.example.supplements.model.entities.UserRoleEntity;
import com.example.supplements.model.enums.UserRoleEnum;
import com.example.supplements.repositories.UserRepository;
import com.example.supplements.repositories.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleSeeder implements CommandLineRunner {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;

    public RoleSeeder(UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRoleRepository.count() == 0) {

            UserRoleEntity adminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setUserRole(UserRoleEnum.USER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);

            User admin = new User();
            admin.setUsername("Admin");
            admin.setPassword("$2a$10$fhfZfQ448u1WXHLJjRSTpO2V6xDus6HSGoogrAFB3uh2SLQq2kqQa");
            admin.setEmail("admin@example.com");
            admin.setUserRoles(List.of(userRole,adminRole));
            this.userRepository.save(admin);
        }
    }
}
