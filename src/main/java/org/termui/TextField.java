package org.termui;

import org.termui.style.CompoundStyle;

class TextField extends Component {
    private StringBuilder text;

    public TextField(int x, int y, int width) {
        super(x, y, width, 1);
        this.text = new StringBuilder();
    }

    public void setText(String text) {
        this.text = new StringBuilder(text);
    }

    public String getText() {
        return text.toString();
    }

    public void appendText(String text) {
        this.text.append(text);
    }

    public void clearText() {
        this.text = new StringBuilder();
    }

    @Override
    protected void drawComponent(char[][] buffer, CompoundStyle[][] styleBuffer, int startX, int startY, int contentWidth, int contentHeight) {
        // style
        //CompoundStyle style = getCompoundStyle();

        // Draw the text field text
        int currentX = startX + 1;
        int currentY = startY + 1;
        for (char c : text.toString().toCharArray()) {
            buffer[currentY][currentX] = c;
            //styleBuffer[currentY][currentX] = style;
            currentX++;
            if (currentX >= startX + contentWidth - 1) {
                currentX = startX + 1;
                currentY++;
                if (currentY >= startY + contentHeight - 1) {
                    break;
                }
            }
        }
    }

    @Override
    public int getActualWidth() {
        return Math.max(getWidth() - 2, 4);
    }

    @Override
    public int getActualHeight() {
        return Math.max(getHeight() - 2, 1);
    }

}