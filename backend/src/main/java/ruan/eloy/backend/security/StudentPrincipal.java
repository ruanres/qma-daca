package ruan.eloy.backend.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ruan.eloy.backend.entity.Student;
import ruan.eloy.backend.entity.Tutor;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class whose instances will be returned from StudentDetailsService.
 * Spring Security will use the information stored in the StudentPrincipal
 * object to perform authentication and authorization.
 */
public class StudentPrincipal implements UserDetails {

    private Long id;

    private String name;

    private String registration;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends  GrantedAuthority> authorities;

    private Set<Tutor> tutors;

    public StudentPrincipal(Long id, String name, String registration, String email,
                            String password, Collection<? extends GrantedAuthority> authorities,
                            Set<Tutor> tutors) {
        this.id = id;
        this.name = name;
        this.registration = registration;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.tutors = tutors;
    }

    public static StudentPrincipal create(Student student) {
        Set<GrantedAuthority> authorities = student.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());

        return new StudentPrincipal(
                student.getId(),
                student.getName(),
                student.getRegistration(),
                student.getEmail(),
                student.getPassword(),
                authorities,
                student.getTutors());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegistration() {
        return registration;
    }

    public String getEmail() {
        return email;
    }

    public Set<Tutor> getTutors() {
        return tutors;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.registration;
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

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        StudentPrincipal that = (StudentPrincipal) obj;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
