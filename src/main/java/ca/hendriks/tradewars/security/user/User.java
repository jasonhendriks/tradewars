package ca.hendriks.tradewars.security.user;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Objects;

@RedisHash
public record User(
        @Indexed
        String id,
        @Indexed
        String email,
        String password,
        boolean disabled,
        boolean accountExpired,
        boolean accountLocked,
        boolean credentialsExpired) {

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

}
