package ru.gb.entity;

import lombok.Data;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
@Scope("session")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "secondName")
    private String secondName;

    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )

    @ManyToMany
    private Collection<Role> roles;

}
