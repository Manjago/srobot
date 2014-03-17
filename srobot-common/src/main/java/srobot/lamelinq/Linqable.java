package srobot.lamelinq;

public interface Linqable<E, F> {
    E findFirst(Predicate<F> predicate);
}
