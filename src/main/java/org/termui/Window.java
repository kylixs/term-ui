package org.termui;

/**
 * @author gongdewei 2023/7/2
 */
public class Window {
    private int width;
    private int height;
    private Panel rootPanel;
    private char[][] mainBuffer;


    public Window(int width, int height) {
        this.width = width;
        this.height = height;
        mainBuffer = new char[height][width];
        rootPanel = new Panel(0, 0, width, height);
        this.clear();
    }

    public void clear() {
        for (char[] row : mainBuffer) {
            for (int i = 0; i < row.length; i++) {
                row[i] = ' ';
            }
        }
    }

    public void draw() {
        rootPanel.draw(mainBuffer);

        for (char[] row : mainBuffer) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public Panel getRootPanel() {
        return rootPanel;
    }
}
