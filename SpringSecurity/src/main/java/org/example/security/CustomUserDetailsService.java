package org.example.security;

import lombok.AllArgsConstructor;
import org.example.persistence.ApplicationUser;
import org.example.persistence.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> result = userRepository.findByUsername(username);
        return result.map(SecurityUser::new).orElseThrow(() -> new UsernameNotFoundException("User: " + username + "not found"));
    }
}
