package org.termui;

import org.termui.layout.HorizontalAlignment;
import org.termui.style.CompoundStyle;

public class Button extends Component {
    private String label;

    public Button(int x, int y, String label) {
        super(x, y, label.length() + 4, 3);
        this.label = label;
        this.horizontalAlignment = HorizontalAlignment.CENTER;
    }

    @Override
    protected void drawComponent(char[][] buffer, CompoundStyle[][] styleBuffer, int startX, int startY, int contentWidth, int contentHeight) {
        // style
        //CompoundStyle style = getCompoundStyle();

        // Draw the button text
        int currentX = startX + (getWidth() - contentWidth) / 2;
        int currentY = startY + (getHeight() - contentHeight) / 2;
        for (char c : label.toCharArray()) {
            buffer[currentY][currentX] = c;
            //styleBuffer[currentY][currentX] = style;
            currentX++;
            if (currentX >= startX + contentWidth) {
                break;
            }
        }
    }

    @Override
    public int getActualWidth() {
        return Math.min(label.length(), getWidth());
    }

    @Override
    public int getActualHeight() {
        return 1;
    }
}