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
    public String toString() {
        return "T";
    }
}
