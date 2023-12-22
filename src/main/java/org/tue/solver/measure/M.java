package org.tue.solver.measure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tue.utils.ArrayUtil;

import java.util.Arrays;

@AllArgsConstructor
@Getter
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
            case M m -> ArrayUtil.arrayEqual(arr, m.getArr());
        };
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
