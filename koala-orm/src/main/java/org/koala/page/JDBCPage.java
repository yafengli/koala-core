package org.koala.page;

import java.util.List;

/**
 * 使用JDBC获取数据内容构建分页信息
 */
public class JDBCPage<T> extends BasePage<T> {

    private List<T> pageElements;
    private int pageSize;
    private int totalNumberOfElements;

    public List<T> getThisPageElements() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getPageSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getTotalNumberOfElements() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getPageNumber() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
