package com.example.supplements.services;

import com.example.supplements.model.dtos.UserRegistrationDto;
import com.example.supplements.model.entities.User;
import com.example.supplements.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private UserDetailsService userDetailsService;

    @Captor
    private ArgumentCaptor<User> userEntityArgumentCaptor;

    private UserService toTest;

    @BeforeEach
    void setUp() {
        toTest = new UserService(
                mockUserRepository,
                userDetailsService,
                mockPasswordEncoder);
    }

    @Test
    void testUserRegistration_SaveInvoked() {

        //LEFT for reference

        // ARRANGE
        UserRegistrationDto testRegistrationDTO = new UserRegistrationDto();
        testRegistrationDTO.setUsername("Pesho");
        testRegistrationDTO.setEmail("pesho@example.com");
        testRegistrationDTO.setPassword("topsecret");

        //ACT

        toTest.registerUser(testRegistrationDTO);

        //ASSERT
        verify(mockUserRepository).save(any());
    }

}
