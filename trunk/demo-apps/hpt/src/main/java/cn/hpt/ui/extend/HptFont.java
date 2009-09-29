package cn.hpt.ui.extend;

import java.awt.Font;

import org.springframework.stereotype.Service;

@Service
public class HptFont {

	public final Font LABLE_SONTI;
	public final Font OPTION_SONTI;
	// public final Font Y_CONSOLE;
	public final Font PRICE_NAME;
	public final Font PRICE_LABEL;

	private static final long serialVersionUID = -3230435859900844074L;

	public HptFont() {
		LABLE_SONTI = new Font("宋体", Font.PLAIN, 14);
		OPTION_SONTI = new Font("宋体", Font.PLAIN, 12);
		PRICE_NAME = new Font("宋体", Font.BOLD, 18);
		PRICE_LABEL = new Font("宋体", Font.BOLD, 12);
		// Y_CONSOLE = new Font("YaHei Consolas Hybrid", Font.PLAIN, 14);
	}
}
