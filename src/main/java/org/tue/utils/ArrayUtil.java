package org.tue.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ArrayUtil {

    public static boolean arrayEqual(int[] one, int[] two) {
        if (one.length != two.length) {
            return false;
        }
        for (int i = 0; i < one.length; i++) {
            if(one[i] != two[i]) {
                return false;
            }
        }
        return true;
    }
}
