package org.example.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;

    @ManyToMany(mappedBy = "authorities")
    private List<ApplicationUser> users;

    public Authority() {
        this.users = new ArrayList<>();
    }

    public Authority(String name) {
        this();
        this.name = name;
    }
}
