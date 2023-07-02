package org.termui.style;

/**
 * @author gongdewei 2023/7/2
 */
public class CompoundStyle {
    // compound multi Style and Colors
    private Style style;
    private Color foregroundColor;
    private Color backgroundColor;

    public CompoundStyle() {
        this(Style.NONE, Color.WHITE, Color.BLACK);
    }

    public CompoundStyle(Style style) {
        this(style, Color.WHITE, Color.BLACK);
    }

    public CompoundStyle(Style style, Color foregroundColor, Color backgroundColor) {
        this.style = style;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
    }

    public CompoundStyle withStyle(Style style) {
        this.style = style;
        return this;
    }

    public CompoundStyle withForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
        return this;
    }

    public CompoundStyle withBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }


}
