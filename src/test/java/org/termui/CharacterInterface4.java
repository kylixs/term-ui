package org.termui;

import org.termui.layout.HorizontalLayout;
import org.termui.layout.TableLayout;
import org.termui.layout.VerticalLayout;

public class CharacterInterface4 {
    private char[][] mainBuffer;
    private Panel rootPanel;

    public CharacterInterface4(int width, int height) {
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
        CharacterInterface4 characterInterface = new CharacterInterface4(80, 24);

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

        Panel rootPanel = new Panel(0, 0, 80, 24);
        rootPanel.setLayoutManager(new TableLayout(2, 1));
        rootPanel.addComponent(panel1);
        rootPanel.addComponent(panel2);

        characterInterface.setRootContainer(rootPanel);
        rootPanel.doLayout();

        characterInterface.draw();
    }
}