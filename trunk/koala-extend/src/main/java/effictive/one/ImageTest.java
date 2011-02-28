package effictive.one;

import javax.swing.*;
import java.awt.event.*;

public class ImageTest {

    public static void main(String[] args) {
        System.out.println(System.getenv("JAVA_HOME")+"|"+System.getProperty("test.base.dir"));
        ImagePanel panel = new ImagePanel(new ImageIcon("f:/tmp/images/background.png").getImage());


        ImageLabel label = new ImageLabel(new ImageIcon("f:/tmp/images/reactor.png"));
        label.setLocation(29,37);
        panel.add(label);

        final ImageButton button = new ImageButton("f:/tmp/images/button.png");
        button.setPressedIcon(new ImageIcon("f:/tmp/images/button-down.png"));
        button.setRolloverIcon(new ImageIcon("f:/tmp/images/button-over.png"));
        button.setSelectedIcon(new ImageIcon("f:/tmp/images/button-sel.png"));
        button.setRolloverSelectedIcon(new ImageIcon("f:/tmp/images/button-sel-over.png"));
        button.setDisabledIcon(new ImageIcon("f:/tmp/images/button-disabled.png"));
        button.setDisabledSelectedIcon(new ImageIcon("f:/tmp/images/button-disabled-selected.png"));
        button.setLocation(60,74);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                button.setSelected(!button.isSelected());
                System.out.println("selecting");
            }
        });
        //button.setSelected(true);
        //button.setDisabled(false);
        panel.add(button);
        
        
        final JCheckBox checkbox = new JCheckBox("Disable");
        checkbox.setLocation(70,150);
        checkbox.setOpaque(false);
        checkbox.setSize(checkbox.getPreferredSize());
        panel.add(checkbox);
        checkbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                button.setEnabled(!checkbox.isSelected());
            }
        });
        
        

        JFrame frame = new JFrame("Hack #XX: Image Components");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
