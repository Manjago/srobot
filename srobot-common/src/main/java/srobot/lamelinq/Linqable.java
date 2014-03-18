package srobot.lamelinq;

public interface Linqable<E> {
    E findFirst(Predicate<E> predicate);
}
