package cn.hpt.ui.listener;

import cn.hpt.util.PropertiesLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(ListenerDefinition.CLOSE_BEAN_NAME)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class CloseAppActionListener extends KeyAdapter implements
        ActionListener {

    @Autowired
    private PropertiesLoader pl;

    @Override
    public void actionPerformed(ActionEvent e) {
        pl.store();
        System.exit(-1);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        actionPerformed(null);
    }
}
