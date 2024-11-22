package ca.hendriks.tradewars;

import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisDataConfiguration {

    @Bean
    public LettuceClientConfigurationBuilderCustomizer lettuceClientConfigurationBuilderCustomizer() {
        return clientConfigurationBuilder -> {
            if (clientConfigurationBuilder.build()
                    .isUseSsl()) {
                clientConfigurationBuilder.useSsl()
                        .disablePeerVerification();
            }
        };
    }

}
