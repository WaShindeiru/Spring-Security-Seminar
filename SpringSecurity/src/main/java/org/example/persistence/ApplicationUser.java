package org.example.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authorities;

    public ApplicationUser() {
        authorities = new ArrayList<>();
    }

    public ApplicationUser(Long userId, String username, String password) {
        this();
        this.id = userId;
        this.username = username;
        this.password = password;
    }

    public ApplicationUser(String username, String password) {
        this(null, username, password);
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
        authority.getUsers().add(this);
    }

    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
        authority.getUsers().remove(this);
    }

    @Override
    public String toString() {
        return "id: " + id + " " + username;
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this ) {
            return true;
        }

        if (obj instanceof ApplicationUser e) {
            return e.username.equals(this.username);
        }

        return false;
    }
}
