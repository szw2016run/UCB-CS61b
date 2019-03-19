package es.datastructur.synthesizer;


import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {//why I don't know

    int capacity();

    int fillCount();

    void enqueue(T x);

    T dequeue();

    T peek();

    default boolean isEmpty() {
        return fillCount()==0;
    }

    default boolean isFall() {
        return capacity()==fillCount();
    }

    Iterator<T> iterator();

}
