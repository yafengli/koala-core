package org.koala.page;

import java.util.List;

import javax.persistence.Query;

/**
 * 使用JPA查询获取数据内容构建分页信息
 */
public class JPAPage<T> implements Page<T> {

    private List<T> elements;
    private Integer pageSize;
    private Integer pageNumber;
    private Integer totalElements = 0;

    /**
     * 构建JPAPage对象，完成JPAPage的Query数据的分页处理
     *
     * @param countQuery
     *            Hibernate的查询总数的Query对象
     * @param pageNumber
     *            当前页编码
     * @param pageSize
     *            每一页显示的条目数
     * @param dataQuery
     *            Hibernate的查询结果的Query对象
     */
    public JPAPage(Query countQuery, int pageNumber, int pageSize,
            Query dataQuery) {
        // 设置
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        // 验证
        if (this.pageNumber < 1) {
            this.pageNumber = 1;
        }
        if (Integer.MAX_VALUE == this.pageNumber || this.pageNumber > getLastPageNumber()) // last page
        {
            this.pageNumber = getLastPageNumber();
        }
        // 注入数据
        try {
            Object obj = countQuery.getSingleResult();
            if (obj != null && obj instanceof Long) {
                this.totalElements = ((Long) obj).intValue();
            } else {
                throw new NumberFormatException("[countQuery is error.]");
            }

            elements = dataQuery.setFirstResult(
                    (this.pageNumber - 1) * this.pageSize).setMaxResults(
                    this.pageSize + 1).getResultList();
        } catch (Exception e) {
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
