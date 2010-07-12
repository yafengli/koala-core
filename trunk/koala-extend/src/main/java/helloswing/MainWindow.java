package helloswing;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: phoenixup
 * Date: 2010-7-9
 * Time: 18:08:05
 */
public class MainWindow extends JFrame {

    public MainWindow() {
        initSwing();
    }

    public void initSwing() {
        setBounds(500, 500, 400, 300);
        setUndecorated(true);
        AWTUtilities.setWindowOpaque(this, false);
        LayoutManager layout = new BorderLayout();
        setLayout(layout);


        mouseInit(this);


        ImagePanel panel_1 = new ImagePanel(new ImageIcon("f:/Tmp/Image.jpg").getImage());
        ImagePanel panel_2 = new ImagePanel(new ImageIcon("f:/Tmp/2.png").getImage());
        ImagePanel panel_3 = new ImagePanel(new ImageIcon("f:/Tmp/3.png").getImage());

        JLabel closeButton = new JLabel(new ImageIcon("f:/Tmp/3.png"));
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(-1);
            }
        });
        panel_3.setLayout(new FlowLayout());
        panel_3.add(closeButton);

        add(panel_2, BorderLayout.NORTH);
        add(panel_1, BorderLayout.CENTER);
        add(panel_3, BorderLayout.SOUTH);
    }

    private void mouseInit(final JFrame jf) {
        MouseAdapter ma = new MouseAdapter() {
            private Point lastPoint = null;

            @Override
            public void mousePressed(MouseEvent e) {
                lastPoint = e.getLocationOnScreen();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point pt = e.getLocationOnScreen();
                int ptx = pt.x - lastPoint.x;
                int pty = pt.y - lastPoint.y;
                Rectangle bounds = jf.getBounds();
                bounds.x += ptx;
                bounds.y += pty;
                jf.setBounds(bounds);
                lastPoint = pt;
            }
        };
        addMouseListener(ma);
        addMouseMotionListener(ma);

    }

    public static void main(String[] args) {
        MainWindow t = new MainWindow();
        t.pack();
        t.setVisible(true);
    }
}

class ImagePanel extends JPanel {

    private Image img;

    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
