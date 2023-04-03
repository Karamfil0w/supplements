package com.example.supplements.services;

import com.example.supplements.model.dtos.MakeOrderDto;
import com.example.supplements.model.dtos.UserRegistrationDto;
import com.example.supplements.model.entities.User;
import com.example.supplements.repositories.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;


        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserRegistrationDto registrationDTO) {

        Optional<User> byUsernameAndEmail = this.userRepository.findByUsernameAndEmail
                (registrationDTO.getUsername(), registrationDTO.getEmail());

        if (byUsernameAndEmail.isEmpty()) {

            User user = new User();
            user.setUsername(registrationDTO.getUsername());
            user.setEmail(registrationDTO.getEmail());
            user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

            userRepository.save(user);

        }
    }
    public void login(String userName) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userName);

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }

    public void makeOrder(MakeOrderDto makeOrderDto) {
        //TODO
    }
}
