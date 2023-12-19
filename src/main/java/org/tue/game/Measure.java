package org.tue.game;

public sealed interface Measure {
    record Top() implements Measure{}
    record M(int[] arr) implements Measure{}

}
