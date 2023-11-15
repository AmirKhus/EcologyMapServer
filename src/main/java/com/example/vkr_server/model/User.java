package com.example.vkr_server.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "username", nullable = false)
    private String username;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role")
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "user_user"),
            inverseJoinColumns = @JoinColumn(name = "user_friend"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_user", "user_friend"})
    )
        private List<User> friends;
    public User() {
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(Long id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public void addFriend(User u) {
        this.friends.add(u);
    }


}
