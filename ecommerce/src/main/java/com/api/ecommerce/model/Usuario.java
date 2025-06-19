package com.api.ecommerce.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "usuarios")
public class Usuario implements UserDetails {

    @Id
    private String id;

    private String nombre;
    private String apellido;
    private String email;

    @ToString.Exclude // Evita mostrar en logs o toString
    @EqualsAndHashCode.Exclude // Evita problemas de comparación por seguridad
    private String password;

    private Role role; // ADMIN o USER

    // Implementación de métodos requeridos por UserDetails:
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Agrega el prefijo ROLE_ por convención de Spring Security
        return List.of(new SimpleGrantedAuthority("ROLE_" + (role != null ? role.name() : "USER")));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; 
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
