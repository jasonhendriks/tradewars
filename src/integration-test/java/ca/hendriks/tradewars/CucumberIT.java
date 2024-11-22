package ca.hendriks.tradewars;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("specification")
@CucumberContextConfiguration
@WebAppConfiguration
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
                        value = "html:target/cucumber-reports/Cucumber.html")
@SpringBootTest(
        classes = {CucumberIT.Config.class, Main.class},
        properties = {"cucumber.publish.enabled=true"})
@EnableAutoConfiguration(exclude = RedisAutoConfiguration.class)
public class CucumberIT {

    @ComponentScan
    static class Config {

        @Bean
        BddMockMvcService bddMockMvcService(final WebApplicationContext webApplicationContext) {
            return new BddMockMvcService(webApplicationContext);
        }

    }

}
