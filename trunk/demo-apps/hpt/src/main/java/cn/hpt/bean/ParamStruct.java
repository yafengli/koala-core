package cn.hpt.bean;

import javax.swing.JTextField;

public class ParamStruct {
	private String label;
	private String labelName;
	private char mnemonic;
	private Integer width;
	private String tip;
	private JTextField field;

	public ParamStruct(String labelName, String label, String tip,
			char mnemonic, Integer width,JTextField field) {
		this.setLabelName(labelName);
		this.setLabel(label);
		this.setTip(tip);
		this.setMnemonic(mnemonic);
		this.setWidth(width);
		this.setField(field);
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public char getMnemonic() {
		return mnemonic;
	}

	public void setMnemonic(char mnemonic) {
		this.mnemonic = mnemonic;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public JTextField getField() {
		return field;
	}

	public void setField(JTextField field) {
		this.field = field;
	}
	
}