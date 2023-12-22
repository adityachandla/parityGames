package org.tue.dto;

import lombok.Getter;

@Getter
public enum Owner {
    ODD,
    EVEN;

    public static Owner parseOwner(String s) {
        if("1".equals(s)) {
            return ODD;
        } else if ("0".equals(s)) {
            return EVEN;
        }
        throw new IllegalArgumentException("Unable to parse " + s);
    }
}
