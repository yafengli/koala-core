/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.demo.annotation;

import org.koala.spring.Properties;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component("loadConfig")
public class LoadConfig {

    @Properties(name = "base.xp.dir")
    private String baseXpDir;
    @Properties(name = "base.linux.dir")
    private String baseLinuxDir;

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
