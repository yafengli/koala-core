package org.koala.page;

import java.util.List;

/**
 * 处理List的分页
 */
public class ListPage<T> implements Page<T> {

    public List<T> elements;//当前页内容
    public int pageSize;// 每页结果数
    public int pageNumber;// 页数
    public int totalNumber;//结果总数

    public ListPage() {
    }

    /**
     * 构建ListPage对象，完成List数据的分页处理
     *
     * @param elements   List数据源
     * @param pageNumber 当前页编码，从1开始，如果传的值为Integer.MAX_VALUE表示获取最后一页。
     *                   如果你不知道最后一页编码，传Integer.MAX_VALUE即可。如果当前页超过总页数，也表示最后一页。
     *                   这两种情况将重新更改当前页的页码，为最后一页编码。
     * @param pageSize   每一页显示的条目数
     */
    public ListPage(List elements, int pageNumber, int pageSize) {
        this.setPageSize(pageSize);
        this.setPageNumber(pageNumber);
        this.setTotalNumber(elements.size());
        this.setElements(elements.subList(getFirstElementNumber(), getLastElementNumber()));

        this.setElements(elements.subList(pageSize * (pageNumber - 1), pageSize * pageNumber < elements.size() ? pageSize * pageNumber : elements.size()));
    }

    /**
     * 构建ListPage对象，完成List数据的分页处理
     *
     * @param elements    List数据源
     * @param totalNumber 总结果数
     * @param pageNumber  当前页编码，从1开始，如果传的值为Integer.MAX_VALUE表示获取最后一页。
     *                    如果你不知道最后一页编码，传Integer.MAX_VALUE即可。如果当前页超过总页数，也表示最后一页。
     *                    这两种情况将重新更改当前页的页码，为最后一页编码。
     * @param pageSize    每一页显示的条目数
     */
    public ListPage(List pageElements, int totalNumber, int pageNumber, int pageSize) {
        this.setPageSize(pageSize);
        this.setPageNumber(pageNumber);
        this.setTotalNumber(totalNumber);
        this.setElements(pageElements);
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
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

        return this.getTotalNumberOfElements() % this.getPageSize() == 0 ? this.getTotalNumberOfElements() / this.getPageSize() : this.getTotalNumberOfElements() / this.getPageSize() + 1;
    }

    /**
     * 返回List类型本页的数据
     *
     * @return List本页的数据
     */
    public List<T> getThisPageElements() {
        return elements;
    }

    public int getTotalNumberOfElements() {
        return totalNumber;
    }

    public int getFirstElementNumber() {
        return (getPageNumber() - 1) * getPageSize();
    }

    public int getLastElementNumber() {
        return getPageNumber() * getPageSize() < getTotalNumberOfElements() ? getPageNumber() * getPageSize() : getTotalNumberOfElements();
    }

    public int getNextPageNumber() {
        return getPageNumber() + 1;
    }

    public int getPreviousPageNumber() {
        return getPageNumber() - 1;
    }

    public int getPageSize() {
        // 每页结果必须为正数
        if (this.pageSize <= 0) {
            this.pageSize = 1;
        }
        return pageSize;
    }

    public int getPageNumber() {
        // 页数必须为正数
        if (this.pageNumber <= 0) {
            this.pageNumber = 1;
        }
        if (pageNumber >= getLastPageNumber()) {
            this.pageNumber = getLastPageNumber();
        }
        return pageNumber;
    }
}
