package org.termui;

import org.termui.border.BorderStyle;
import org.termui.layout.HorizontalLayout;
import org.termui.style.Color;

/**
 * @author gongdewei 2023/7/2
 */
public class HorizontalLayoutTest {

        public static void main(String[] args) {
            Window window = new Window(80, 24);
            Panel rootPanel = window.getRootPanel();

            HorizontalLayout layoutManager = new HorizontalLayout();
            rootPanel.setLayoutManager(layoutManager);

            Panel leftPanel = new Panel(0, 0, 20, 24);
            leftPanel.setBackgroundColor(Color.BLUE);
            leftPanel.setBorderStyle(BorderStyle.SINGLE_LINE);

            Panel rightPanel = new Panel(20, 0, 60, 24);
            rightPanel.setBorderStyle(BorderStyle.SINGLE_LINE);

            rootPanel.addComponent(leftPanel);
            rootPanel.addComponent(rightPanel);
            window.draw();
        }
}
