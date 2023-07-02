package org.termui;

import org.termui.border.BorderStyle;

public class Label extends Component {
    private String text;

    public Label(int x, int y, String text) {
        super(x, y, text.length(), 1);
        this.text = text;
    }

    public Label(String text) {
        super(0, 0, text.length(), 1);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Label withBorder(BorderStyle borderStyle) {
        return super.withBorder(borderStyle);
    }

    @Override
    protected void drawComponent(char[][] buffer, int startX, int startY, int contentWidth, int contentHeight) {
        // Draw the label text
        int currentX = startX;
        int currentY = startY;
        for (char c : text.toCharArray()) {
            buffer[currentY][currentX] = c;
            currentX++;
            if (currentX >= startX + contentWidth) {
                currentX = startX;
                currentY++;
                if (currentY >= startY + contentHeight) {
                    break;
                }
            }
        }
    }

    @Override
    public int getActualWidth() {
        int maxLineWidth = 0;
        for (String line : text.split("\n")) {
            maxLineWidth = Math.max(maxLineWidth, line.length());
        }
        int width = Math.min(maxLineWidth, getWidth());
        if (borderStyle != BorderStyle.NONE) {
            width += 2;
        }
        return width;
    }

    @Override
    public int getActualHeight() {
        String[] lines = text.split("\n");
        return Math.min(lines.length, getHeight());
    }

    public String toString() {
        return "Label[" + text + "]";
    }
}