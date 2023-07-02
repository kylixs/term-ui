package org.termui;

import org.termui.layout.VerticalLayout;

public class VerticalLayoutTest {

    public static void main(String[] args) {
        Window window = new Window(80, 24);
        Panel rootPanel = window.getRootPanel();

        Panel panel = new Panel(10, 5, 60, 14);
        panel.setLayoutManager(new VerticalLayout());

        Label label1 = new Label(0, 0, "Label 1");
        panel.addComponent(label1);

        Label label2 = new Label(0, 0, "Label 2");
        panel.addComponent(label2);

        Label label3 = new Label(0, 0, "Label 3");
        panel.addComponent(label3);

        rootPanel.addComponent(panel);

        window.doLayout();
        window.draw();
    }
}