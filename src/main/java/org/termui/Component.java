package org.termui;

import org.termui.border.BorderStyle;
import org.termui.layout.HorizontalAlignment;
import org.termui.layout.VerticalAlignment;
import org.termui.style.Color;
import org.termui.style.CompoundStyle;
import org.termui.style.CompoundStyleFactory;
import org.termui.style.Style;

public class Component {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected VerticalAlignment verticalAlignment = VerticalAlignment.TOP;
    protected HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
    protected Style style = Style.NONE;
    protected Color foregroundColor = Color.NONE;
    protected Color backgroundColor = Color.NONE;
    protected BorderStyle borderStyle = BorderStyle.NONE;
    protected Color borderColor = Color.WHITE;
    protected Component parent;

    public Component(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.visible = true;
    }

    // set parent
    protected void setParent(Component parent) {
        this.parent = parent;
    }

    public void setAlignment(HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
        this.horizontalAlignment = horizontalAlignment;
    }

    protected void drawBackground(char[][] buffer, CompoundStyle[][] styleBuffer) {
        CompoundStyle style = getCompoundStyle();
        int startY = getY();
        int startX = getX();
        int contentHeight = getHeight();
        int contentWidth = getWidth();
        for (int y = startY; y < startY + contentHeight; y++) {
            for (int x = startX; x < startX + contentWidth; x++) {
                // avoid out of bounds of buffer
                if (y < buffer.length && x < buffer[y].length) {
                    buffer[y][x] = ' ';
                    styleBuffer[y][x] = style;
                }
            }
        }
    }

    public void draw(char[][] buffer, CompoundStyle[][] styleBuffer) {
        int startX = x;
        int startY = y;

        // Perform horizontal alignment
        int actualWidth = getActualWidth();
        int actualHeight = getActualHeight();
//        if (borderStyle != BorderStyle.NONE) {
//            actualWidth += 2;
//            //actualHeight += 2;
//        }

        switch (horizontalAlignment) {
            case LEFT:
                startX = x;
                break;
            case CENTER:
                startX = x + (width - actualWidth) / 2;
                break;
            case RIGHT:
                startX = x + width - actualWidth;
                break;
            default:
                startX = x;
                break;
        }

        // Perform vertical alignment
        switch (verticalAlignment) {
            case TOP:
                startY = y;
                break;
            case MIDDLE:
                startY = y + (height - actualHeight) / 2;
                break;
            case BOTTOM:
                startY = y + height - actualHeight;
                break;
            default:
                startY = y;
                break;
        }

        // Draw the background
        drawBackground(buffer, styleBuffer);

        // Draw the border
        if (borderStyle != BorderStyle.NONE) {
            // Adjust the start position
            startX++;
//            startY++;
            drawBorder(buffer, styleBuffer);
        }

        // Draw the component
        drawComponent(buffer, styleBuffer, startX, startY, actualWidth, actualHeight);

        System.out.println(String.format("Component: %s, bounds:(%d, %d, %d, %d), draw: (%d, %d)", this, x, y, getWidth(), getHeight(), startX, startY));
    }

    protected void drawComponent(char[][] buffer, CompoundStyle[][] styleBuffer, int startX, int startY, int contentWidth, int contentHeight) {
        // Do nothing, override in subclasses
    }

//    protected void drawBorder(char[][] buffer, int startX, int startY, int width, int height) {
//        if (height < 2 || width < 2) {
//            return;
//        }
//        for (int i = 0; i < width; i++) {
//            buffer[startY][startX + i] = '-';
//            buffer[startY + height - 1][startX + i] = '-';
//        }
//        for (int i = 0; i < height; i++) {
//            buffer[startY + i][startX] = '|';
//            buffer[startY + i][startX + width - 1] = '|';
//        }
//        buffer[startY][startX] = '+';
//        buffer[startY][startX + width - 1] = '+';
//        buffer[startY + height - 1][startX] = '+';
//        buffer[startY + height - 1][startX + width - 1] = '+';
//    }

    protected void drawBorder(char[][] buffer, CompoundStyle[][] styleBuffer) {
        if (borderStyle == BorderStyle.NONE) {
            return;
        }

        char horizontalLineChar;
        char verticalLineChar;
        char topLeftCornerChar;
        char topRightCornerChar;
        char bottomLeftCornerChar;
        char bottomRightCornerChar;

        switch (borderStyle) {
            case SINGLE_LINE:
                horizontalLineChar = '-';
                verticalLineChar = '|';
                topLeftCornerChar = '+';
                topRightCornerChar = '+';
                bottomLeftCornerChar = '+';
                bottomRightCornerChar = '+';
                break;
            case DOUBLE_LINE:
                horizontalLineChar = '=';
                verticalLineChar = '║';
                topLeftCornerChar = '╔';
                topRightCornerChar = '╗';
                bottomLeftCornerChar = '╚';
                bottomRightCornerChar = '╝';
                break;
            // 其他边框样式的定义...
            default:
                return;
        }

        int startX = x;
        int startY = y;
        int endX = x + width - 1;
        int endY = y + height - 1;

        // 绘制水平边框线
        for (int i = startX + 1; i < endX; i++) {
            buffer[startY][i] = horizontalLineChar;
            buffer[endY][i] = horizontalLineChar;
        }

        // 绘制垂直边框线
        for (int i = startY + 1; i < endY; i++) {
            buffer[i][startX] = verticalLineChar;
            buffer[i][endX] = verticalLineChar;
        }

        // 绘制边框角落
        buffer[startY][startX] = topLeftCornerChar;
        buffer[startY][endX] = topRightCornerChar;
        buffer[endY][startX] = bottomLeftCornerChar;
        buffer[endY][endX] = bottomRightCornerChar;
    }

    public CompoundStyle getCompoundStyle() {
        Style _style = getStyle();
        Color _foregroundColor = getForegroundColor();
        Color _backgroundColor = getBackgroundColor();
        if (parent != null) {
            // if style is none use parent style
            if (_style == Style.NONE) {
                _style = parent.getCompoundStyle().getStyle();
            }
            // if foreground color is none use parent foreground color
            if (_foregroundColor == Color.NONE) {
                _foregroundColor = parent.getCompoundStyle().getForegroundColor();
            }
            // if background color is none use parent background color
            if (_backgroundColor == Color.NONE) {
                _backgroundColor = parent.getCompoundStyle().getBackgroundColor();
            }
        }
        return CompoundStyleFactory.get(_style, _foregroundColor, _backgroundColor);
    }

    public int getActualWidth() {
        // Override in subclasses if necessary
        return width;
    }

    public int getActualHeight() {
        // Override in subclasses if necessary
        return height;
    }

    protected char getCharAt(int x, int y) {
        return ' ';
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setBounds(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public <T extends Component> T withBorder(BorderStyle borderStyle) {
        this.borderStyle = borderStyle;
        return (T) this;
    }

    // with foreground color
    public <T extends Component> T withForegroundColor(Color color) {
        this.foregroundColor = color;
        return (T) this;
    }

    // with background color
    public <T extends Component> T withBackgroundColor(Color color) {
        this.backgroundColor = color;
        return (T) this;
    }

    // with style
    public <T extends Component> T withStyle(Style style) {
        this.style = style;
        return (T) this;
    }

    // with vertical alignment
    public <T extends Component> T withVerticalAlignment(VerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
        return (T) this;
    }

    // with horizontal alignment
    public <T extends Component> T withHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
        return (T) this;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Style getStyle() {
        return style;
    }

    public void setForegroundColor(Color color) {
        this.foregroundColor = color;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBorderStyle(BorderStyle borderStyle) {
        this.borderStyle = borderStyle;
    }

    public BorderStyle getBorderStyle() {
        return borderStyle;
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
    }

    public Color getBorderColor() {
        return borderColor;
    }

}

