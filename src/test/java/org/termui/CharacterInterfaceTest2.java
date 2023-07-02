package org.termui;

import org.termui.layout.VerticalLayout;

public class CharacterInterfaceTest2 {
    private char[][] mainBuffer;
    private Panel rootPanel;

    public CharacterInterfaceTest2(int width, int height) {
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
        CharacterInterfaceTest2 characterInterface = new CharacterInterfaceTest2(80, 24);

        Panel panel = new Panel(10, 5, 60, 14);
        panel.setLayoutManager(new VerticalLayout());

        Label label1 = new Label(0, 0, "Label 1");
        panel.addComponent(label1);

        Label label2 = new Label(0, 0, "Label 2");
        panel.addComponent(label2);

        Label label3 = new Label(0, 0, "Label 3");
        panel.addComponent(label3);

        characterInterface.setRootContainer(panel);
        panel.doLayout();

        characterInterface.draw();
    }
}