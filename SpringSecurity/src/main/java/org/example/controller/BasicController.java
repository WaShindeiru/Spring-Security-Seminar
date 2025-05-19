package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.ApplicationUserDto;
import org.example.persistence.ApplicationUser;
import org.example.persistence.UserRepository;
import org.example.security.SecurityUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class BasicController {

    private final UserRepository userRepository;
    private final SimpleService simpleService;

    @PostMapping("/a")
    public StatusDto postEndpointA() {
        return new StatusDto("Ok", "Post /a works!");
    }

    @GetMapping("/a")
    public StatusDto getEndpointA() {
        return new StatusDto("Ok", "Get /a works!");
    }

    @GetMapping("/a/b")
    public StatusDto getEnpointB() {
        return new StatusDto("Ok", "Get /a/b works!");
    }

    @GetMapping("/a/b/c")
    public StatusDto getEnpointC() {
        return new StatusDto("Ok", "Get /a/b/c works!");
    }

    @GetMapping("/method/example")
    public StatusDto getMethodSecurity() {
        return simpleService.getMethodSecurityResult();
    }

    @GetMapping("/method/books")
    public List<Book> getBooks() {
        var auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return simpleService.getBooks((SecurityUser) auth.getPrincipal());
    }

    @GetMapping("/method/user")
    public ResponseEntity<?> getUser() {
        var auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        Optional<ApplicationUser> temp = userRepository.findByUsername(auth.getName());
        if (temp.isPresent()) {
            return ResponseEntity.ok(ApplicationUserDto.from(temp.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
