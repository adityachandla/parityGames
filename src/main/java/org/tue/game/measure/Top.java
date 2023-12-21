package org.tue.game.measure;

public final class Top implements Measure {
    private static final Top instance = new Top();

    private Top() {
    }

    public static Top getInstance() {
        return Top.instance;
    }

    @Override
    public Measure getMin(Measure other) {
        return switch (other) {
            case Top t -> t;
            case M m -> m;
        };
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
}
