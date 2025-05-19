package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.persistence.ApplicationUser;

import java.util.List;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class ApplicationUserDto {

    private Long id;
    private String username;
    private String password;

    private List<AuthorityDto> securityAuthorities;

    public static ApplicationUserDto from(ApplicationUser applicationUser) {
        ApplicationUserDto applicationUserDto = new ApplicationUserDto();
        applicationUserDto.setId(applicationUser.getId());
        applicationUserDto.setUsername(applicationUser.getUsername());
        applicationUserDto.setPassword(applicationUser.getPassword());

        applicationUserDto.setSecurityAuthorities(applicationUser.getAuthorities().stream().map(AuthorityDto::from).toList());

        return applicationUserDto;
    }
}
