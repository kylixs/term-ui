package org.termui.style;

public enum Color {
    NONE(-1),
    BLACK(30),
    RED(31),
    GREEN(32),
    YELLOW(33),
    BLUE(34),
    MAGENTA(35),
    CYAN(36),
    WHITE(37);

    private int code;

    Color(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}