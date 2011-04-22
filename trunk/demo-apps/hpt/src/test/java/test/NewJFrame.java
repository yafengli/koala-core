package test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class NewJFrame extends javax.swing.JFrame {

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NewJFrame inst = new NewJFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public NewJFrame() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

			Container contentPane = getContentPane();

			SpringLayout layout = new SpringLayout();
			final JPanel jcpc = new JPanel();
			jcpc.setLayout(new BorderLayout());
			final JPanel jcp = new JPanel();
			contentPane.setLayout(new BorderLayout());
			jcpc.add(jcp, BorderLayout.CENTER);
			contentPane.add(jcpc, BorderLayout.CENTER);

			jcp.setLayout(layout);

			Component left = new JLabel("Left");
			Component right = new JTextField(15);
			JButton button = new JButton("Print");

			jcp.add(left);
			jcp.add(right);
			jcp.add(button);
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					Image img = jcpc.createImage(jcpc.getWidth(), jcpc
							.getHeight());
					Graphics imgG = img.getGraphics();
					jcpc.paint(imgG);
					BufferedImage bi = new BufferedImage(200, 200,
							BufferedImage.TYPE_INT_RGB);
					Graphics g = bi.getGraphics();
					g.drawImage(img, 0, 0, null);
					try {
						ImageIO.write(bi, "jpg", new File("f:/test.jpg"));
						jcpc.printAll(jcpc.getGraphics());
						// PrintableComponent pc = new PrintableComponent(jcpc);
						// pc.print();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});

			layout.putConstraint(SpringLayout.WEST, left, 10,
					SpringLayout.WEST, contentPane);
			layout.putConstraint(SpringLayout.NORTH, left, 25,
					SpringLayout.NORTH, contentPane);
			layout.putConstraint(SpringLayout.NORTH, right, 25,
					SpringLayout.NORTH, contentPane);
			layout.putConstraint(SpringLayout.WEST, right, 20,
					SpringLayout.EAST, left);
			contentPane.setPreferredSize(new Dimension(260, 50));
			setVisible(true);
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
