package org.termui;

import org.termui.layout.HorizontalLayout;
import org.termui.layout.TableLayout;
import org.termui.layout.VerticalLayout;

public class TableLayoutTest {

    public static void main(String[] args) {
        Window window = new Window(80, 24);
        Panel rootPanel = window.getRootPanel();

        Panel panel1 = new Panel(10, 5, 60, 14);
        panel1.setLayoutManager(new VerticalLayout());

        Label label1 = new Label(0, 0, "Label 1");
        panel1.addComponent(label1);

        Label label2 = new Label(0, 0, "Label 2");
        panel1.addComponent(label2);

        Panel panel2 = new Panel(0, 0, 60, 4);
        panel2.setLayoutManager(new HorizontalLayout());

        Label label3 = new Label(0, 0, "Label 3");
        panel2.addComponent(label3);

        Label label4 = new Label(0, 0, "Label 4");
        panel2.addComponent(label4);

        rootPanel.setLayoutManager(new TableLayout(2, 1));
        rootPanel.addComponent(panel1);
        rootPanel.addComponent(panel2);

        window.doLayout();
        window.draw();
    }
}