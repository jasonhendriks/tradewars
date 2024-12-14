package ca.hendriks.tradewars.web.security.user.registration;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        // no-op
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final UserCreationView user = (UserCreationView) obj;
        return user.password()
                .equals(user.passwordConfirmation());
    }

}
