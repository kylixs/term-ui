package org.termui;

import org.termui.style.CompoundStyle;
import org.termui.style.StyleUtil;

/**
 * @author gongdewei 2023/7/2
 */
public class Window {
    private int width;
    private int height;
    private Panel rootPanel;
    private char[][] mainBuffer;
    private CompoundStyle[][] styleBuffer;

    public Window(int width, int height) {
        this.width = width;
        this.height = height;
        mainBuffer = new char[height][width];
        styleBuffer = new CompoundStyle[height][width];
        rootPanel = new Panel(0, 0, width, height);
        this.clear();
    }

    public void clear() {
        for (char[] row : mainBuffer) {
            for (int i = 0; i < row.length; i++) {
                row[i] = ' ';
            }
        }
        for (CompoundStyle[] row : styleBuffer) {
            for (int i = 0; i < row.length; i++) {
                row[i] = new CompoundStyle();
            }
        }
    }

    public void draw() {
        rootPanel.draw(mainBuffer, styleBuffer);

        // draw with style
        for (int y = 0; y < mainBuffer.length; y++) {
            for (int x = 0; x < mainBuffer[y].length; x++) {
                CompoundStyle style = styleBuffer[y][x];
                if (style != null) {
                    System.out.print(StyleUtil.beginStyle(style));
                }
                System.out.print(mainBuffer[y][x]);
                if (style != null) {
                    System.out.print(StyleUtil.resetStyle());
                }
            }
            System.out.println();
        }

//        for (char[] row : mainBuffer) {
//            for (char c : row) {
//                System.out.print(c);
//            }
//            System.out.println();
//        }

    }

    public Panel getRootPanel() {
        return rootPanel;
    }

    public void doLayout() {
        rootPanel.doLayout();
    }
}
