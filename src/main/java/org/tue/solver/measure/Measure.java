package org.tue.solver.measure;

public sealed interface Measure permits Top, M {
    Measure getMin(Measure other);

    Measure getMax(Measure other);

    boolean measureEqual(Measure other);

    /**
     *
     * @param maximum The maximum possible M value for the game.
     * @param idx The idx up to which we have to compare.
     * @return Updated measure or Top.
     */
    Measure increment(M maximum, int idx);

    /**
     * Compares the current
     * @param other other measure
     * @param idx index to compare till. Comparison happens from idx -> 0.
     * @return GREATER if the current measure is greater than other
     * LESSER if the current measure is less than the other
     * EQUAL if the current measure is equal to the other.
     */
    CompareResult compareTillIndex(Measure other, int idx);
}
