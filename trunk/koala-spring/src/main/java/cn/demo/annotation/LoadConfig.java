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

    @Properties(name = "base.dir")
    private String baseDir;

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }
}
