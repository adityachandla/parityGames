package org.tue.solver.measure;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public final class M implements Measure {
    private final int[] arr;

    @Override
    public Measure getMin(Measure other) {
        return switch (other) {
            case Top ignored -> this;
            case M m -> computeMin(m);
        };
    }

    private Measure computeMin(M other) {
        for (int i = 0; i < arr.length; i++) {
            if (other.arr[i] < arr[i]) {
                return other;
            } else if (other.arr[i] > arr[i]) {
                return this;
            }
        }
        return this;
    }

    public int get(int idx) {
        return this.arr[idx];
    }

    public void increment(int idx) {
        this.arr[idx]++;
    }

    public int size() {
        return this.arr.length;
    }

    @Override
    public Measure getMax(Measure other) {
        return switch (other) {
            case Top t -> t;
            case M m -> computeMax(m);
        };
    }

    @Override
    public boolean measureEqual(Measure other) {
        return switch (other) {
            case Top ignored -> false;
            case M m -> arrayEqual(arr, m);
        };
    }

    private boolean arrayEqual(int[] arr, M other) {
        if (arr.length != other.size()) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != other.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Measure increment(M maximum, int idx) {
        for (int i = idx; i >= 0; i--) {
            if (arr[i] < maximum.get(i)) {
                var newM = new M(Arrays.copyOf(arr, arr.length));
                newM.increment(i);
                return newM;
            }
        }
        return Top.getInstance();
    }

    @Override
    public CompareResult compareTillIndex(Measure other, int idx) {
        if (other instanceof Top) {
            return CompareResult.LESSER;
        }
        var otherArray = ((M) other);
        for (int i = 0; i <= idx; i++) {
            if (arr[i] > otherArray.get(i)) {
                return CompareResult.GREATER;
            } else if (arr[i] < otherArray.get(i)) {
                return CompareResult.LESSER;
            }
        }
        return CompareResult.EQUAL;
    }

    private Measure computeMax(M other) {
        for (int i = 0; i < arr.length; i++) {
            if (other.arr[i] < arr[i]) {
                return this;
            } else if (other.arr[i] > arr[i]) {
                return other;
            }
        }
        return other;
    }

    @Override
    public String toString() {
        return String.format("M(%s)", Arrays.toString(arr));
    }
}
