package cn.demo.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author Administrator
 */
@Configuration
@ImportResource("classpath:/applicationContext.xml")
public class LoadConfig {

    private
    @Value("#{cp.xp}")
    String baseXpDir;
    private
    @Value("#{cp.linux}")
    String baseLinuxDir;

    public String getBaseXpDir() {
        return baseXpDir;
    }

    public void setBaseXpDir(String baseXpDir) {
        this.baseXpDir = baseXpDir;
    }

    public String getBaseLinuxDir() {
        return baseLinuxDir;
    }

    public void setBaseLinuxDir(String baseLinuxDir) {
        this.baseLinuxDir = baseLinuxDir;
    }
}
