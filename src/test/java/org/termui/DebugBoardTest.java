package org.termui;

import org.termui.border.BorderStyle;
import org.termui.layout.NineGridLayoutManager;
import org.termui.layout.Position;
import org.termui.style.Color;

/**
 * @author gongdewei 2023/7/2
 */
public class DebugBoardTest {

    public static void main(String[] args) {
        Window window = new Window(80, 24);
        Panel rootPanel = window.getRootPanel();
        rootPanel.withBackgroundColor(Color.BLUE);

        NineGridLayoutManager layoutManager = new NineGridLayoutManager(rootPanel);
        rootPanel.setLayoutManager(layoutManager);

        Panel topPanel = new Panel(0, 0, 80, 1);
        topPanel.setBackgroundColor(Color.BLUE);
        topPanel.setBorderStyle(BorderStyle.SINGLE_LINE);

        Panel centerPanel = new Panel(0, 1, 80, 12);
        centerPanel.setBorderStyle(BorderStyle.SINGLE_LINE);

        Panel bottomPanel = new Panel(0, 13, 80, 10);
        bottomPanel.setBorderStyle(BorderStyle.SINGLE_LINE);

        layoutManager.addComponent(topPanel, Position.TOP);
        rootPanel.addComponent(centerPanel);
        rootPanel.addComponent(bottomPanel);
        window.draw();
    }
}
