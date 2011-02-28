package helloswing.hacks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by IntelliJ IDEA.
 * User: phoenixup
 * Date: 2010-9-17
 * Time: 14:19:17
 * To change this template use File | Settings | File Templates.
 */
public class ImagePanel extends JPanel {
    private Image img;

    public ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(this.img.getWidth(null), this.img.getHeight(null));
        setSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public static void main(String args[]) {
        ImagePanel panel = new ImagePanel(new ImageIcon("F:\\Photo\\路过\\1.jpg").getImage());
        ImageLabel label = new ImageLabel(new ImageIcon("F:\\Photo\\1.gif"));
        final ImageButton button = new ImageButton(new ImageIcon("F:\\Photo\\路过\\绿豆蛙\\u=3559901058,4212647091&gp=16.GIF"));
        JFrame frame = new JFrame("Hello World!");

        label.setLocation(0, 0);
        panel.add(label);
        button.setLocation(60, 80);
        panel.add(button);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        final JCheckBox checkbox = new JCheckBox("Disable");
        checkbox.setLocation(70, 150);
        checkbox.setOpaque(false);
        checkbox.setSize(checkbox.getPreferredSize());
        panel.add(checkbox);
        checkbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                button.setEnabled(!checkbox.isSelected());
            }
        });

    }

    static class ImageLabel extends JLabel {
        ImageLabel(Icon image) {
            setSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
            setIcon(image);
            setIconTextGap(0);
            setBorder(null);
            setText(null);
            setOpaque(false);
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("What the fuck");
                    System.exit(-1);
                }
            });
        }
    }

    static class ImageButton extends JButton {
        ImageButton(Icon icon) {
            setSize(icon.getIconWidth(), icon.getIconHeight());
            setIcon(icon);
            setMargin(new Insets(0, 0, 0, 0));
            setIconTextGap(0);
            setBorderPainted(false);
            setBorder(null);
            setText(null);
        }
    }
}

