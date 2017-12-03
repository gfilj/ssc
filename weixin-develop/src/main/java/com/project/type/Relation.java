package com.project.type;

/**
 * Created by goforit on 2017/12/3.
 */
public enum Relation {
    Subscrib(1), Unsubscrib(2);
    private int value;

    Relation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
