package org.tue.game.measure;

public sealed interface Measure permits Top, M {
    Measure getMin(Measure other);

    Measure getMax(Measure other);

    boolean measureEqual(Measure other);
}
