package uz.hojiakbar.newprogect.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.hojiakbar.newprogect.Entity.User;
import uz.hojiakbar.newprogect.repository.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getLogin();
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
        };
    }
}
