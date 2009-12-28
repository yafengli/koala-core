package org.koala.page;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;

/**
 * 使用Hibernate查询数据内容构建分页信息
 */
public class HibernatePage<T> implements Page<T> {

    private List<T> elements;
    private int pageSize;
    private int pageNumber;
    private int totalElements = 0;

    /**
     * 构建HibernatePage对象，完成Hibernate的Query数据的分页处理
     *
     * @param query
     *            Hibernate的Query对象
     * @param pageNumber
     *            当前页编码，从1开始，如果传的值为Integer.MAX_VALUE表示获取最后一页。
     *            如果你不知道最后一页编码，传Integer.MAX_VALUE即可。如果当前页超过总页数，也表示最后一页。
     *            这两种情况将重新更改当前页的页码，为最后一页编码。
     * @param pageSize
     *            每一页显示的条目数
     */
    public HibernatePage(Query query, int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        try {
            ScrollableResults scrollableResults = query.scroll();
            // get the total elements number
            scrollableResults.last();
            this.totalElements = scrollableResults.getRowNumber();
            if (Integer.MAX_VALUE == this.pageNumber || this.pageNumber > getLastPageNumber()) // last page
            {
                this.pageNumber = getLastPageNumber();
            }
            elements = query.setFirstResult(
                    (this.pageNumber - 1) * this.pageSize).setMaxResults(
                    this.pageSize + 1).list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isFirst() {
        return getPageNumber() == 1;
    }

    public boolean isLast() {
        return getPageNumber() >= getLastPageNumber();
    }

    public boolean hasNext() {
        return getLastPageNumber() > getPageNumber();
    }

    public boolean hasPrevious() {
        return getPageNumber() > 1;
    }

    public int getLastPageNumber() {
        return totalElements % this.pageSize == 0 ? totalElements / this.pageSize : totalElements / this.pageSize + 1;
    }

    /**
     * 返回List类型数据
     *
     * @return List数据源
     */
    public List<T> getThisPageElements() {
        return elements;
    }

    public int getTotalNumberOfElements() {
        return totalElements;
    }

    public int getFirstElementNumber() {
        return (getPageNumber() - 1) * getPageSize() + 1;
    }

    public int getLastElementNumber() {
        int fullPage = getFirstElementNumber() + getPageSize() - 1;
        return getTotalNumberOfElements() < fullPage ? getTotalNumberOfElements()
                : fullPage;
    }

    public int getNextPageNumber() {
        return getPageNumber() + 1;
    }

    public int getPreviousPageNumber() {
        return getPageNumber() - 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
