package ca.hendriks.tradewars.web.security.user.registration;

class EmailExistsException extends Throwable {

    public EmailExistsException(final String message) {
        super(message);
    }

}
