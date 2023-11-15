package com.example.newultramegaproject.init;

import com.example.newultramegaproject.domain.ProductType;
import com.example.newultramegaproject.domain.Role;
import com.example.newultramegaproject.domain.Customer;
import com.example.newultramegaproject.repository.ProductTypeRepository;
import com.example.newultramegaproject.repository.RoleRepository;
import com.example.newultramegaproject.repository.CustomerRepository;
import com.example.newultramegaproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDatabase implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final ProductTypeRepository productTypeRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;


    @Override
    @Transactional
    public void run(String... args){
        var roleAdmin = roleRepository.save(new Role("ROLE_ADMIN"));
        var roleUser = roleRepository.save(new Role("ROLE_USER"));
        var roleGuest = roleRepository.save(new Role("ROLE_GUEST"));
        Customer user = new Customer("user", passwordEncoder.encode("user123"),"0981231231","asd@gmail.com");
        user.addRole(roleGuest);
        user.addRole(roleUser);
        Customer admin = new Customer("admin", passwordEncoder.encode("admin123"),"0981231231","asd@gmail.com");
        admin.addRole(roleAdmin);
        customerService.save(user);
        customerService.save(admin);
        productTypeRepository.save(new ProductType("М'ясо"));
        productTypeRepository.save(new ProductType("Корма"));
    }
}
