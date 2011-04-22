package test;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class New2JDialog extends javax.swing.JDialog {
	private JPanel jPanel1;
	private JButton jButton1;
	private JButton jButton8;
	private JButton jButton7;
	private JButton jButton6;
	private JButton jButton5;
	private JButton jButton4;
	private JButton jButton3;
	private JButton jButton2;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				New2JDialog inst = new New2JDialog(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public New2JDialog(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				jPanel1 = new JPanel();
				FormLayout jPanel1Layout = new FormLayout(
						"max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu)", 
						"max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu)");
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				{
					jButton1 = new JButton();
					jPanel1.add(jButton1, new CellConstraints("1, 1, 1, 1, default, default"));
					jButton1.setText("jButton1");
				}
				{
					jButton2 = new JButton();
					jPanel1.add(jButton2, new CellConstraints("2, 1, 1, 1, default, default"));
					jButton2.setText("jButton2");
				}
				{
					jButton3 = new JButton();
					jPanel1.add(jButton3, new CellConstraints("3, 1, 1, 1, default, default"));
					jButton3.setText("jButton3");
				}
				{
					jButton4 = new JButton();
					jPanel1.add(jButton4, new CellConstraints("4, 1, 1, 1, default, default"));
					jButton4.setText("jButton4");
				}
				{
					jButton5 = new JButton();
					jPanel1.add(jButton5, new CellConstraints("1, 2, 1, 1, default, default"));
					jButton5.setText("jButton5");
				}
				{
					jButton6 = new JButton();
					jPanel1.add(jButton6, new CellConstraints("2, 2, 1, 1, default, default"));
					jButton6.setText("jButton6");
				}
				{
					jButton7 = new JButton();
					jPanel1.add(jButton7, new CellConstraints("3, 2, 1, 1, default, default"));
					jButton7.setText("jButton7");
				}
				{
					jButton8 = new JButton();
					jPanel1.add(jButton8, new CellConstraints("4, 2, 1, 1, default, default"));
					jButton8.setText("jButton8");
				}
			}
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
