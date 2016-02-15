package com.jquantium.util.auto;

import com.jquantium.dao.ORM;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static com.jquantium.util.Assert.NULL;

/**
 * Created by Mykhailo_Bohdanov on 10/02/2016.
 */
public abstract class AList<E> {
    private ORM orm;

    protected List<E> elementList;

    private boolean addElement(E element) {
        return true;//TODO
    }

    private boolean updateElement(E element) {
        return true;//TODO
    }

    private boolean removeElement(E element) {
        return true;//TODO
    }

    public AList(ORM orm) {
        this.orm = orm;

        elementList = init();

        if (NULL(elementList)) {
            elementList = new ArrayList<>();
        }
    }
    public AList(ORM orm, List<E> elementList) {
        this(orm);

        if (!NULL(elementList)) {
            this.addAll(elementList);
        }
    }

    public boolean create(E element) {
        if (!NULL(element) && onCreate(element)) {
            return add(element);
        }

        return false;
    }
    public boolean add(E element) {
        if (NULL(element)) {
            return false;
        }

        return elementList.add(element) && addElement(element);
    }

    public boolean addAll(List<E> elementList) {
        if (NULL(elementList)) {
            return false;
        }

        elementList.forEach(this::add);
        return true;
    }

    public E get(int index) {
        return elementList.get(index);
    }
    public List<E> getAll() {
        return elementList;
    }

    public void forEach(Consumer<? super E> action){
        if (elementList != null) {
            Objects.requireNonNull(action);
            elementList.forEach(action::accept);
        }
    }

    public E delete(int index) {
        return delete(elementList.get(index));
    }
    public E delete(E element) {
        if (!NULL(element) && onDelete(element)) {
            elementList.remove(element);
//            unbindElement(element);
            removeElement(element);

            return element;
        }

        return null;
    }

    public abstract List<E> init();

    protected boolean onCreate(E element) {
        try {
            orm.insert(element);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
    protected boolean onUpdate(E element) {
        try {
            orm.update(element);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
    protected boolean onDelete(E element) {
        try {
            orm.delete(element);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
