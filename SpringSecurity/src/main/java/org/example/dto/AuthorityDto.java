package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.persistence.Authority;

@AllArgsConstructor
@Getter
@Setter
public class AuthorityDto {
    private String authority;

    public static AuthorityDto from(Authority securityAuthority) {
        return new AuthorityDto(securityAuthority.getName());
    }
}
