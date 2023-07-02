package org.termui;

import org.termui.border.BorderStyle;
import org.termui.layout.AdaptiveLayout;
import org.termui.layout.Constraint;
import org.termui.layout.ConstraintType;

import java.util.ArrayList;
import java.util.List;

public class AdaptiveLayoutTest {
    private char[][] mainBuffer;
    private Panel rootContainer;

    public AdaptiveLayoutTest(int width, int height) {
        mainBuffer = new char[height][width];
        rootContainer = new Panel(0, 0, width, height);
    }

    public void setRootContainer(Panel container) {
        rootContainer = container;
    }

    public void draw() {
        for (char[] row : mainBuffer) {
            for (int i = 0; i < row.length; i++) {
                row[i] = ' ';
            }
        }

        rootContainer.draw(mainBuffer);

        for (char[] row : mainBuffer) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        AdaptiveLayoutTest adaptiveLayoutTest = new AdaptiveLayoutTest(80, 24);

        Panel container = new Panel(10, 5, 60, 14);
        container.withBorder(BorderStyle.SINGLE_LINE);
        container.addComponent(new Label(0, 0, "Label 1").withBorder(BorderStyle.SINGLE_LINE));
        container.addComponent(new Label(0, 0, "Label 2").withBorder(BorderStyle.SINGLE_LINE));
        container.addComponent(new Label(0, 0, "Label 3").withBorder(BorderStyle.SINGLE_LINE));

        List<Constraint> constraints = new ArrayList<>();
        constraints.add(new Constraint(1, ConstraintType.FIXED));
        constraints.add(new Constraint(1, ConstraintType.FILL));
        constraints.add(new Constraint(1, ConstraintType.FILL));

        container.setLayoutManager(new AdaptiveLayout(false, constraints));
        adaptiveLayoutTest.setRootContainer(container);
        container.doLayout();

        adaptiveLayoutTest.draw();
    }
}