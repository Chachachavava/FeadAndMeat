package com.example.newultramegaproject.repository;

import com.example.newultramegaproject.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
    @Query("from Customer u join fetch u.authorities where u.username=:username")
    Optional<Customer> findByUsernameWithRoles(@Param("username") String username);
}
