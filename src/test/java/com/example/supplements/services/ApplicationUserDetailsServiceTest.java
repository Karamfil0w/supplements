package com.example.supplements.services;

import com.example.supplements.model.entities.User;
import com.example.supplements.model.entities.UserRoleEntity;
import com.example.supplements.model.enums.UserRoleEnum;
import com.example.supplements.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ApplicationUserDetailsServiceTest {

    private final String NOT_EXISTING_USERNAME = "pesho";

    @Mock
    private ApplicationUserDetailsService toTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new ApplicationUserDetailsService(
                mockUserRepository
        );
    }

    @Test
    void testUserFound() {

        // ARRANGE
        UserRoleEntity testAdminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
        UserRoleEntity testUserRole = new UserRoleEntity().setUserRole(UserRoleEnum.USER);

        String EXISTING_USERNAME = "Admin";

        User testUser = new User().
                setUsername(EXISTING_USERNAME).
                setPassword("topsecret").
                setUserRoles(List.of(testAdminRole, testUserRole));


        when(mockUserRepository.findByUsername(EXISTING_USERNAME)).
                thenReturn(Optional.of(testUser));
        // EO: ARRANGE


        // ACT
        UserDetails adminDetails =
                toTest.loadUserByUsername(EXISTING_USERNAME);
        // EO: ACT

        // ASSERT
        Assertions.assertNotNull(adminDetails);
        Assertions.assertEquals(EXISTING_USERNAME, adminDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(), adminDetails.getPassword());

        Assertions.assertEquals(2,
                adminDetails.getAuthorities().size(),
                "The authorities are supposed to be just two - ADMIN/USER.");

        assertRole(adminDetails.getAuthorities(), "ROLE_ADMIN");
        assertRole(adminDetails.getAuthorities(), "ROLE_USER");
        // EO: ASSERT
    }

    private void assertRole(Collection<? extends GrantedAuthority> authorities,
                            String role) {
        authorities.
                stream().
                filter(a -> role.equals(a.getAuthority())).
                findAny().
                orElseThrow(() -> new AssertionFailedError("Role " + role + " not found!"));
    }


    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTING_USERNAME)
        );
    }
}
