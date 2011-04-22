package test;

import org.junit.Test;

import java.awt.Color;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class BgTest {
	JFrame frame;
	JScrollPane scroll;
	JTable table;
	JTableHeader tableH;

	public BgTest() {
		frame = new JFrame("JTableTest");
		Object[][] content = { { "1", "2", "3", "4" }, { "a", "b", "c", "d" } };
		String[] title = { "玩", "世", "不", "恭" };
		table = new JTable(content, title);
		// JTable的背景颜色设置
		table.setBackground(new Color(230, 230, 230));
		// JTable没有选中的文字颜色
		table.setForeground(new Color(0, 0, 0));
		// JTable边线颜色
		table.setGridColor(new Color(100, 100, 100));
		// 获得表头
		tableH = table.getTableHeader();
		// 设置表头的背景色
		tableH.setBackground(new Color(200, 200, 200));
		// 设置表头的文字颜色
		tableH.setForeground(new Color(0, 0, 0));
		scroll = new JScrollPane(table);
		// JScrollPane的背景色设置
		scroll.getViewport().setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(scroll);
		frame.setSize(360, 120);
		frame.setVisible(true);
	}
     @Test
    public  void test(String[] args) {
        float f=0.343434f;
        NumberFormat nf=NumberFormat.getInstance();
        System.out.println(nf.format(f));
	}
}
