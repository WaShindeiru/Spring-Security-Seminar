package org.example.persistence;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
@AllArgsConstructor
public class DataInserter implements CommandLineRunner {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Authority basic_user = new Authority("ROLE_BASIC_USER");
        Authority admin = new Authority("ROLE_ADMIN");
        Authority manager = new Authority("ROLE_MANAGER");

        ApplicationUser Ola = new ApplicationUser("Ola", "12345");
        ApplicationUser Maciek = new ApplicationUser("Maciek", "12345");
        ApplicationUser Zuza = new ApplicationUser("Zuza", "12345");

        List<ApplicationUser> users = List.of(Ola, Maciek, Zuza);

        for (ApplicationUser user : users) {
            user.addAuthority(basic_user);
        }

        Maciek.addAuthority(manager);
        Zuza.addAuthority(admin);

        userRepository.saveAll(users);
    }
}
