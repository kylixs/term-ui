package org.termui;

import java.util.ArrayList;
import java.util.List;

class TableTest {
    private char[][] mainBuffer;
    private List<Component> components;

    public TableTest(int width, int height) {
        mainBuffer = new char[height][width];
        components = new ArrayList<>();
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public void draw() {
        for (char[] row : mainBuffer) {
            for (int i = 0; i < row.length; i++) {
                row[i] = ' ';
            }
        }

        for (Component component : components) {
            component.draw(mainBuffer);
        }

        for (char[] row : mainBuffer) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TableTest characterInterface = new TableTest(80, 24);

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
        characterInterface.addComponent(table);

        characterInterface.draw();
    }
}
