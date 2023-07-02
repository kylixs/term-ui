package org.termui;

public class Button extends Component {
    private String label;

    public Button(int x, int y, String label) {
        super(x, y, label.length() + 4, 3);
        this.label = label;
    }

//    @Override
//    protected char getCharAt(int x, int y) {
//        if (y == 0 || y == 2) {
//            if (x == 0 || x == width - 1) {
//                return '+';
//            } else if (x == 1 || x == width - 2) {
//                return '-';
//            }
//        } else if (y == 1) {
//            if (x == 0 || x == width - 1) {
//                return '|';
//            } else if (x > 1 && x < width - 2) {
//                return label.charAt(x - 2);
//            }
//        }
//        return ' ';
//    }

    @Override
    protected void drawComponent(char[][] buffer, int startX, int startY, int contentWidth, int contentHeight) {
        // Draw the button text
        int currentX = startX + (getWidth() - contentWidth) / 2;
        int currentY = startY + (getHeight() - contentHeight) / 2;
        for (char c : label.toCharArray()) {
            buffer[currentY][currentX] = c;
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