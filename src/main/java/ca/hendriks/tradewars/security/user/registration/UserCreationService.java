package ca.hendriks.tradewars.security.user.registration;

import ca.hendriks.tradewars.security.user.User;
import ca.hendriks.tradewars.security.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class UserCreationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreationService.class);

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    UserCreationService(final UserRepository repository, final PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerNewUser(final UserCreationView user) throws EmailExistsException {
        final String email = user.email();
        if (emailExist(email)) {
            throw new EmailExistsException("There is an account with that email address: " + email);
        }
        final String id = UUID.randomUUID()
                .toString();
        final String passwordEncoded = passwordEncoder.encode(user.password());
        final User newUser = new User(id, email, passwordEncoded, false, false, false, false);
        final User savedUser = repository.save(newUser);
        LOGGER.info("Created user {}", savedUser);
    }

    boolean emailExist(final String email) {
        return repository.findByEmail(email)
                .isPresent();
    }

}
