package org.koala.page;

import java.util.List;

/**
 * 分页信息接口
 * Date: 2009-08-01
 * Time: 09:06
 *
 * @author YaFengLi
 */
public interface Page<T> {

    /**
     * @return 是否是首页（第一页），第一页页码为1
     */
    public boolean isFirst();

    /**
     * @return 是否是最后一页
     */
    public boolean isLast();

    /**
     * @return 是否有下一页
     */
    public boolean hasNext();

    /**
     * @return 是否有上一页
     */
    public boolean hasPrevious();


    /**
     * @return 当前页的数据内容
     */
    public List<T> getThisPageElements();

    /**
     * @return 数据总的条目数量
     */
    public int getTotalNumberOfElements();

    /**
     * @return 当前页的首条数据的行编码
     */
    public int getFirstElementNumber();

    /**
     * @return 当前页的末条数据的行编码
     */
    public int getLastElementNumber();

    /**
     * @return 当前页编码
     */
    public int getPageNumber();

    /**
     * @return 下一页编码
     */
    public int getNextPageNumber();

    /**
     * @return 上一页编码
     */
    public int getPreviousPageNumber();

    /**
     * @return 获取最后一页页码，也就是总页数
     */
    public int getLastPageNumber();

    /**
     * @return 每一页显示的条目数
     */
    public int getPageSize();
}
