package org.termui;

import org.termui.layout.HorizontalLayout;
import org.termui.layout.TableLayout;
import org.termui.layout.VerticalLayout;

public class CharacterInterface3 {
    private char[][] mainBuffer;
    private Panel rootPanel;

    public CharacterInterface3(int width, int height) {
        mainBuffer = new char[height][width];
        rootPanel = new Panel(0, 0, width, height);
    }

    public void setRootContainer(Panel panel) {
        rootPanel = panel;
    }

    public void draw() {
        for (char[] row : mainBuffer) {
            for (int i = 0; i < row.length; i++) {
                row[i] = ' ';
            }
        }

        rootPanel.draw(mainBuffer);

        for (char[] row : mainBuffer) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CharacterInterface3 characterInterface = new CharacterInterface3(80, 24);

        Panel panel = new Panel(10, 5, 60, 14);

        VerticalLayout verticalLayout = new VerticalLayout();
        panel.setLayoutManager(verticalLayout);

        Label label1 = new Label(0, 0, "Label 1");
        panel.addComponent(label1);

        Label label2 = new Label(0, 0, "Label 2");
        panel.addComponent(label2);

        Label label3 = new Label(0, 0, "Label 3");
        panel.addComponent(label3);

        characterInterface.setRootContainer(panel);
        panel.doLayout();

        characterInterface.draw();

        System.out.println();

        panel = new Panel(10, 5, 60, 14);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        panel.setLayoutManager(horizontalLayout);

        Button button1 = new Button(0, 0, "Button 1");
        panel.addComponent(button1);

        Button button2 = new Button(0, 0, "Button 2");
        panel.addComponent(button2);

        Button button3 = new Button(0, 0, "Button 3");
        panel.addComponent(button3);

        characterInterface.setRootContainer(panel);
        panel.doLayout();

        characterInterface.draw();

        System.out.println();

        panel = new Panel(10, 5, 60, 14);

        TableLayout tableLayout = new TableLayout(3, 2);
        panel.setLayoutManager(tableLayout);

        TextField textField1 = new TextField(0, 0, 20);
        panel.addComponent(textField1);

        TextField textField2 = new TextField(0, 0, 20);
        panel.addComponent(textField2);

        TextField textField3 = new TextField(0, 0, 20);
        panel.addComponent(textField3);

        characterInterface.setRootContainer(panel);
        panel.doLayout();

        characterInterface.draw();
    }
}