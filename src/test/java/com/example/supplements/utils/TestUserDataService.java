package com.example.supplements.utils;

import com.example.supplements.model.user.SupplementsUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TestUserDataService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new SupplementsUserDetails(1L,
                "topsecret",
                username,
                "Test",
                Collections.emptyList());
    }
}
