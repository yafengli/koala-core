package hellospring;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author phoenixup
 */
@Configuration("configBean")
public class ConfigBean {

    @Value("#{props['test.name']}")
    private String name;

    public String getName() {
        return name;
    }
}
