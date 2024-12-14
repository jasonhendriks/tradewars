package ca.hendriks.tradewars.web.security.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

        @Id
        private String id;
        private String email;
        private String password;
        private boolean disabled;
        private boolean accountExpired;
        private boolean accountLocked;
        private boolean credentialsExpired;

        User() {
        }

        public User(final String id, final String email, final String passwordEncoded, final boolean disabled) {
                this.id = id;
                this.email = email;
                this.password = passwordEncoded;
                this.disabled = disabled;
                this.accountExpired = false;
                this.accountLocked = false;
                this.credentialsExpired = false;
        }

        @Override
        public boolean equals(final Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                final User user = (User) o;
                return Objects.equals(id, user.id);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id);
        }

        @Override
        public String toString() {
                return "User{" +
                        "id='" + id + '\'' +
                        '}';
        }

        public String id() {
                return id;
        }

        public String email() {
                return email;
        }

        public String password() {
                return password;
        }

        public boolean disabled() {
                return disabled;
        }

        public boolean accountExpired() {
                return accountExpired;
        }

        public boolean accountLocked() {
                return accountLocked;
        }

        public boolean credentialsExpired() {
                return credentialsExpired;
        }

}
