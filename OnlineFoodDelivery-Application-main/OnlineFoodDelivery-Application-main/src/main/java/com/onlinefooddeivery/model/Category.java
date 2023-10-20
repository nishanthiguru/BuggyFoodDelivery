package com.onlinefooddeivery.model;

import java.util.HashMap;
import java.util.Map;

public enum Category {
    BRIYANI(0),
    CHICKEN(1),
    SOUTHINDIANTHALI(2),
    DOSAI(3),
    POORI(4),
    BURGER(5);

    private int value;
    private static Map map = new HashMap<>();

    private Category(int value) {
        this.value = value;
    }

    static {
        for (Category category : Category.values()) {
            map.put(category.value, category);
        }
    }

    public static Category valueOf(int category) {
        return (Category) map.get(category);
    }

    public int getValue() {
        return value;
    }
}
