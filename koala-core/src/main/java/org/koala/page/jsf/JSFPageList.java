package org.koala.page.jsf;

import org.koala.page.ListPage;

public abstract class JSFPageList<T> extends ListPage<T> {
	private int goPageNumber;

	public int getGoPageNumber() {
		return goPageNumber;
	}

	public void setGoPageNumber(int goPageNumber) {
		this.goPageNumber = goPageNumber;
	}

	/**
	 * 跳转到某一页
	 * @return 
	 */
	public String go() {
		this.setPageNumber(this.getGoPageNumber());
		return "success";
	}

	/**
	 * 跳转到下一页
	 * @return
	 */
	public String next() {
		this.setGoPageNumber(this.getNextPageNumber());
		return go();
	}
	/**
	 * 跳转到前一页
	 * @return
	 */
	public String previous() {
		this.setGoPageNumber(this.getPreviousPageNumber());
		return go();
	}

	/**
	 * 跳转到末尾页
	 * @return
	 */
	public String last() {
		this.setGoPageNumber(this.getLastPageNumber());
		return go();
	}
	/**
	 * 跳转到首页
	 * @return
	 */
	public String first() {
		this.setGoPageNumber(1);
		return go();
	}
}
