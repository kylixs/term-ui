package org.termui.layout;

public class Constraint {
    private int value;
    private ConstraintType type;

    public Constraint(int value, ConstraintType type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public ConstraintType getType() {
        return type;
    }
}