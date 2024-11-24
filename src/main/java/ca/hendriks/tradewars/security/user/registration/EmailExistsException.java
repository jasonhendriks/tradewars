package ca.hendriks.tradewars.security.user.registration;

class EmailExistsException extends Throwable {

    public EmailExistsException(final String message) {
        super(message);
    }

}
