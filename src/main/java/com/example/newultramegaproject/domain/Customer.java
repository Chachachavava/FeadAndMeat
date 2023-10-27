package com.example.newultramegaproject.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String username;
    @NonNull
    @NotNull
    @NotBlank
    private String password;
    @NonNull
    @NotNull
    @NotBlank
    @Pattern(regexp = "\\+?3?8?\\(?0\\d{2}\\)?\\d{3}-?\\d{2}-?\\d{2}")
    private String phoneNumber;
    @NonNull
    @NotNull
    @NotBlank
    @Pattern(regexp = "\\S+@\\w+\\.\\w+")
    private String email;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
    @ManyToMany(mappedBy = "users")
    private Set<Role> authorities = new HashSet<>();
    @OneToOne
    private ShoppingCart shoppingCart;
    @OneToOne
    private OrderList orders;


    public void addRole(Role role) {
        authorities.add(role);
        role.getUsers().add(this);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, phoneNumber, email, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, authorities);
    }


}
