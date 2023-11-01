package ru.bazhanov.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    public static final String ROLE_RENTER = "RENTER";
    public static final String ROLE_LESSOR = "LESSOR";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "roles",cascade = {
                                                CascadeType.PERSIST,
                                                CascadeType.MERGE
                                              },fetch = FetchType.LAZY)
    private Set<User> users;
    Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String getAuthority() {
        return getName();
    }

}
