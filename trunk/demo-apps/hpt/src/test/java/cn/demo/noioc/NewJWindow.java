package cn.demo.noioc;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

public class NewJWindow extends JWindow {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JPanel jPanel1;
	private JTextField jTextField1;
	private JProgressBar jProgressBar1;
	private JPanel jPanel2;

	public NewJWindow(){
		super();
		initGUI();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NewJWindow inst = new NewJWindow();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});

	}
	public void initGUI(){
		{
			jPanel1 = new JPanel();
			getContentPane().add(jPanel1, BorderLayout.CENTER);
			{
				jTextField1 = new JTextField();
				jPanel1.add(jTextField1);
				jTextField1.setText("陌上曾有花飘过！");
				jTextField1.setPreferredSize(new java.awt.Dimension(186, 64));
			}
		}
		{
			jPanel2 = new JPanel();
			getContentPane().add(jPanel2, BorderLayout.SOUTH);
			{
				jProgressBar1 = new JProgressBar();
				jPanel2.add(jProgressBar1);
			}
		}
		pack();
	}

}
