package org.termui;

class TableTest {

    public static void main(String[] args) {
        Window window = new Window(80, 24);
        Panel rootPanel = window.getRootPanel();

        Table table = new Table(10, 5, 3, 3);
        table.setColumnWidth(0, 15);
        table.setColumnStretchable(1, false);
        table.setRowStretchable(1, false);
        table.setData(0, 0, "Name");
        table.setData(0, 1, "Age");
        table.setData(0, 2, "City");
        table.setData(1, 0, "John");
        table.setData(1, 1, "25");
        table.setData(1, 2, "New York");
        table.setData(2, 0, "Emily");
        table.setData(2, 1, "30");
        table.setData(2, 2, "London");
        rootPanel.addComponent(table);

        window.draw();
    }
}
