package cn.hpt.ui.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractListModel;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class ItemsListModel extends AbstractListModel {

    private List items;

    public <T> List<T> getItems() {
        if (items == null) {
            items = new ArrayList<T>();
        }
        return items;
    }

    public <T> void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public Object getElementAt(int index) {
        if (getItems() != null) {
            return String.format("%s-%s", index, getItems().get(index).toString());
        } else {
            return null;
        }
    }

    @Override
    public int getSize() {
        if (getItems() != null) {
            return getItems().size();
        } else {
            return 0;
        }
    }

    public void add(Object element) {
        if (getItems().add(element)) {
            fireContentsChanged(this, 0, getSize());
        }
    }

    public void addAll(Object elements[]) {
        Collection c = Arrays.asList(elements);
        getItems().addAll(c);
        fireContentsChanged(this, 0, getSize());
    }

    public void clear() {
        getItems().clear();
        fireContentsChanged(this, 0, getSize());
    }

    public boolean contains(Object element) {
        return getItems().contains(element);
    }

    public Object firstElement() {
        return getItems().size() > 0 ? getItems().get(0) : null;
    }

    public Iterator iterator() {
        return getItems().iterator();
    }

    public Object lastElement() {
        return getItems().size() > 0 ? getItems().get(getItems().size() - 1) : 0;
    }

    public boolean removeElement(Object element) {
        boolean removed = getItems().remove(element);
        if (removed) {
            fireContentsChanged(this, 0, getSize());
        }
        return removed;
    }

    public void removeAll() {
        getItems().clear();
        fireContentsChanged(this, 0, getSize());
    }
}
