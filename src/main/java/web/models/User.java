package web.models;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private boolean enabled;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    
    
    public void addRole(Role role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
    @Override
    public String getUsername() {
        return login;
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
        return enabled;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(name, user.name)
                && Objects.equals(surname, user.surname)
                && Objects.equals(email, user.email)
                && enabled == user.enabled
                && Objects.equals(roles, user.roles);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name, surname, email, enabled, roles);
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", isEnabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}


