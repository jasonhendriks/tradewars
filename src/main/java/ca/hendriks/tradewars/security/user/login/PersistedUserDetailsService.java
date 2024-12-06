package ca.hendriks.tradewars.security.user.login;

import ca.hendriks.tradewars.security.user.User;
import ca.hendriks.tradewars.security.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
class PersistedUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistedUserDetailsService.class);
    private static final String ROLE_USER = "ROLE_USER";

    private final UserRepository userRepository;

    public PersistedUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) {
        return userRepository.findByEmail(email)
                .map(this::convert)
                .orElseThrow(() -> createUsernameNotFoundException(email));
    }

    UsernameNotFoundException createUsernameNotFoundException(final String email) {
        return new UsernameNotFoundException("No user found with email: %s".formatted(email));
    }

    UserDetails convert(final User user) {
        LOGGER.info("User {} logging in", user);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.email())
                .password(user.password())
                .disabled(false)
                .accountExpired(user.accountExpired())
                .accountLocked(user.accountLocked())
                .credentialsExpired(user.credentialsExpired())
                .authorities(getAuthorities())
                .build();
    }

    Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(ROLE_USER));
    }

}
