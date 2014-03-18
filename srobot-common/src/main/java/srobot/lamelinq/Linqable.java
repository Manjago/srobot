package srobot.lamelinq;

import java.util.List;

public interface Linqable<E> {
    E findFirst(Predicate<E> predicate);
    List<E> findAll(Predicate<E> predicate);
}
