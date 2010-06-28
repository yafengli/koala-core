package cn.demo.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author Administrator
 */
@Configuration
public class LoadConfig {

    private
    @Value("#{cp.xp}")
    String baseWinDir;
    private
    @Value("#{cp.linux}")
    String baseLinuxDir;

    public String getBaseWinDir() {
        return baseWinDir;
    }

    public void setBaseWinDir(String baseXpDir) {
        this.baseWinDir = baseXpDir;
    }

    public String getBaseLinuxDir() {
        return baseLinuxDir;
    }

    public void setBaseLinuxDir(String baseLinuxDir) {
        this.baseLinuxDir = baseLinuxDir;
    }
}
