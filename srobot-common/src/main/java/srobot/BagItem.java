package srobot;

public interface BagItem<E, K> extends Comparable<E>{
    K getKey();
}
