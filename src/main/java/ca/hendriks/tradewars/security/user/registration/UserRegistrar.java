package ca.hendriks.tradewars.security.user.registration;

import ca.hendriks.tradewars.security.user.User;
import ca.hendriks.tradewars.security.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
class UserRegistrar {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrar.class);

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    UserRegistrar(final UserRepository repository, final PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerNewUser(final UserCreationView view) throws EmailExistsException {
        final String email = view.email();
        final Optional<User> existingUser = repository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw emailExists(email);
        }
        final String id = UUID.randomUUID()
                .toString();
        final String passwordEncoded = passwordEncoder.encode(view.password());
        final User newUser = new User(id, email, passwordEncoded, true);
        final User savedUser = repository.save(newUser);
        LOGGER.info("Created user {}", savedUser);
    }

    private EmailExistsException emailExists(final String email) {
        return new EmailExistsException("There is an account with that email address: " + email);
    }

}
