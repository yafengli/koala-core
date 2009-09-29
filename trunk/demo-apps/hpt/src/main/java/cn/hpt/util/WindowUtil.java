package cn.hpt.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class WindowUtil {

	public static Point center(Component c) {
		Dimension winSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (winSize.width - c.getWidth()) / 2;
		int y = (winSize.height - c.getHeight()) / 2;
		return new Point(x, y);
	}
}
