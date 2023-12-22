package org.tue.solver.measure;

public final class Top implements Measure {
    private static final Top instance = new Top();

    private Top() {
    }

    public static Top getInstance() {
        return Top.instance;
    }

    @Override
    public Measure getMin(Measure other) {
        return other;
    }

    @Override
    public Measure getMax(Measure other) {
        return this;
    }

    @Override
    public boolean measureEqual(Measure other) {
        return switch (other) {
            case Top ignored -> true;
            case M ignored -> false;
        };
    }

    @Override
    public Measure increment(M maximum, int idx) {
        return this;
    }

    @Override
    public CompareResult compareTillIndex(Measure other, int idx) {
        return switch (other) {
            case Top ignored -> CompareResult.EQUAL;
            case M ignored -> CompareResult.GREATER;
        };
    }

    @Override
    public String toString() {
        return "T";
    }
}
