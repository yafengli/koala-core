package cn.demo.noioc;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class NewJFrame extends javax.swing.JFrame {

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JPanel jPanel1;
	private JButton jButton1;
	private JMenu jMenu4;
	private JMenu jMenu3;
	private JMenu jMenu2;
	private JMenu jMenu1;
	private JMenuBar jMenuBar1;
	private JMenu jMenu5;
	private JMenuItem jMenuItem2;
	private JSeparator jSeparator1;
	private JMenuItem jMenuItem1;
	private JTable jTable1;
	private JButton jButton5;
	private JButton jButton4;
	private JButton jButton3;
	private JButton jButton2;
	private JPanel jPanel2;
	private JTabbedPane jTabbedPane1;

	/* listener */
	private OwnActionLitener oa;
	private PrintActionLitener pa;

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
		init();
	}

	private void init() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(thisLayout);
			{
				jMenuBar1 = new JMenuBar();
				oa = new OwnActionLitener();
				pa = new PrintActionLitener();
				setJMenuBar(jMenuBar1);
				{
					jMenu1 = new JMenu();
					jMenuBar1.add(jMenu1);
					jMenu1.setText("测试(F)");
					{
						jMenuItem1 = new JMenuItem();
						jMenu1.add(jMenuItem1);
						jMenuItem1.setText("\u6253\u5f00");
					}
					{
						jSeparator1 = new JSeparator();
						jMenu1.add(jSeparator1);
					}
					{
						jMenuItem2 = new JMenuItem();
						jMenu1.add(jMenuItem2);
						jMenu1.setMnemonic(KeyEvent.VK_F);
						jMenuItem2.setText("关闭");
						jMenuItem2.setAccelerator(KeyStroke.getKeyStroke(
								KeyEvent.VK_Q, Event.ALT_MASK, true));
						jMenuItem2.addActionListener(oa);
					}
				}
				{
					jMenu2 = new JMenu();
					jMenuBar1.add(jMenu2);
					jMenu2.setText("jMenu2");
				}
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("jMenu3");
				}
				{
					jMenu4 = new JMenu();
					jMenuBar1.add(jMenu4);
					jMenu4.setText("jMenu4");
				}
				{
					jMenu5 = new JMenu();
					jMenuBar1.add(jMenu5);
					jMenu5.setText("jMenu5");
				}
			}
			{
				jPanel1 = new JPanel();
				FlowLayout jPanel1Layout = new FlowLayout();
				jPanel1Layout.setAlignment(FlowLayout.LEFT);
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1, BorderLayout.NORTH);
				{
					jButton1 = new JButton();
					jPanel1.add(jButton1);
					jButton1.setText("One");
					{

						jButton1.addActionListener(oa);
					}
				}
				{
					jButton2 = new JButton();
					jPanel1.add(jButton2);
					jButton2.setText("Two");
				}
				{
					jButton3 = new JButton();
					jPanel1.add(jButton3);
					jButton3.setText("Three");
				}
			}
			{
				jTabbedPane1 = new JTabbedPane();
				BorderLayout jTabbedPane1Layout = new BorderLayout();
				getContentPane().add(jTabbedPane1, BorderLayout.CENTER);
				jTabbedPane1.setLayout(jTabbedPane1Layout);
				{
					TableModel jTable1Model = new DefaultTableModel(
							new String[][] { { "One", "Two" },
									{ "Three", "Four" } }, new String[] {
									"Column 1", "Column 2" });
					jTable1 = new JTable();
					jTabbedPane1.addTab("jTable1", null, jTable1, null);
					jTable1.setModel(jTable1Model);
				}
			}
			{
				jPanel2 = new JPanel();
				FlowLayout jPanel2Layout = new FlowLayout();
				jPanel2Layout.setAlignment(FlowLayout.RIGHT);
				jPanel2.setLayout(jPanel2Layout);
				getContentPane().add(jPanel2, BorderLayout.SOUTH);
				{
					jButton4 = new JButton();
					jPanel2.add(jButton4);
					jButton4.setText("\u63d0\u4ea4");
					jButton4.addActionListener(pa);
				}
				{
					jButton5 = new JButton();
					jPanel2.add(jButton5);
					jButton5.setText("\u6d4b\u8bd5");
				}
			}
			pack();
			setResizable(true);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class OwnActionLitener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Hello World!");
			System.exit(-1);
		}
	}

	class PrintActionLitener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Print ....");
			boolean flag=false;
			try {
				int op = JOptionPane.showConfirmDialog(null, "您确认打印吗？", "打印",
						JOptionPane.YES_NO_OPTION);
				switch (op) {
				case 0:					
					flag=jTable1.print();
					if(!flag){
						JOptionPane.showMessageDialog(null, "打印失败，请检查打印机是否正常工作！");
					}
					break;
				case 1:				
					JOptionPane.showMessageDialog(null, "您取消了打印！");
//					System.exit(-1);
					break;
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "请检查打印机，可能没有正常工作！");
				ex.printStackTrace();
			}			
		}
	}
}
