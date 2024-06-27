package com.etalent.etalent_backend.service;

import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.repository.AdminRegisterRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private AdminRegisterRepository adminRegisterRepository;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Admin admin = adminRegisterRepository.findByCorreoAdmin(usernameOrEmail)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not exists by email: " + usernameOrEmail)
                );

        List<SimpleGrantedAuthority> authorities = admin.getRolAdmins().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getNombreRol()))
                .collect(Collectors.toList());

        return new User(usernameOrEmail, admin.getContraAdmin(), authorities);
    }
}
