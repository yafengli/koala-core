package org.koala.page;

/**
 *
 * @author YaFengLi
 */
public abstract class BasePage<T> implements Page<T> {

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
}
