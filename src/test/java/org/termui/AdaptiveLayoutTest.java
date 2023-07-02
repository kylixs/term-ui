package org.termui;

import org.termui.border.BorderStyle;
import org.termui.layout.AdaptiveLayout;
import org.termui.layout.Constraint;
import org.termui.layout.ConstraintType;

import java.util.ArrayList;
import java.util.List;

public class AdaptiveLayoutTest {

    public static void main(String[] args) {

        Window window = new Window(80, 24);

        Panel container = new Panel(10, 5, 60, 14);
        container.withBorder(BorderStyle.SINGLE_LINE);
        container.addComponent(new Label(0, 0, "Top").withBorder(BorderStyle.SINGLE_LINE));
        container.addComponent(new Label(0, 0, "Label 2").withBorder(BorderStyle.SINGLE_LINE));
        container.addComponent(new Label(0, 0, "Label 3").withBorder(BorderStyle.SINGLE_LINE));
        container.addComponent(new Label(0, 0, "Bottom").withBorder(BorderStyle.SINGLE_LINE));

        List<Constraint> constraints = new ArrayList<>();
        constraints.add(new Constraint(1, ConstraintType.FIXED));
        constraints.add(new Constraint(3, ConstraintType.FILL));
        constraints.add(new Constraint(2, ConstraintType.FILL));
        constraints.add(new Constraint(1, ConstraintType.FIXED));

        Panel rootPanel = window.getRootPanel();
        rootPanel.withBorder(BorderStyle.SINGLE_LINE);
        rootPanel.addComponent(container);
        container.setLayoutManager(new AdaptiveLayout(false, constraints));
        container.doLayout();

        window.draw();
    }
}