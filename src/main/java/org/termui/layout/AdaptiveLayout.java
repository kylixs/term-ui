package org.termui.layout;

import org.termui.Component;
import org.termui.Panel;

import java.util.List;

public class AdaptiveLayout implements LayoutManager {
    private boolean isHorizontal;
    private List<Constraint> constraints;

    public AdaptiveLayout(boolean isHorizontal, List<Constraint> constraints) {
        this.isHorizontal = isHorizontal;
        this.constraints = constraints;
    }

    @Override
    public void layoutComponents(Panel container) {
        List<Component> components = container.getComponents();
        int containerX = container.getX();
        int containerY = container.getY();
        int containerWidth = container.getWidth();
        int containerHeight = container.getHeight();

        int totalFixed = 0;
        int totalFill = 0;

        for (Constraint constraint : constraints) {
            if (constraint.getType() == ConstraintType.FIXED) {
                totalFixed += constraint.getValue();
            } else if (constraint.getType() == ConstraintType.FILL) {
                totalFill += constraint.getValue();
            }
        }

        int fixedSize = isHorizontal ? containerWidth - totalFill : containerHeight - totalFill;
        int fillSize = isHorizontal ? containerWidth - totalFixed : containerHeight - totalFixed;
        int remainingSize = isHorizontal ? containerWidth : containerHeight;

        int currentX = containerX;
        int currentY = containerY;

        // set fixed components
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            Constraint constraint = constraints.get(i);
            if (isHorizontal) {
                int componentWidth;
                if (constraint.getType() == ConstraintType.FIXED) {
                    componentWidth = constraint.getValue();
                }
            }
        }

        // set filled components
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            Constraint constraint = constraints.get(i);

            if (isHorizontal) {
                int componentWidth;
                if (constraint.getType() == ConstraintType.FIXED) {
                    componentWidth = constraint.getValue();
                } else {
                    componentWidth = (int) ((double) constraint.getValue() / totalFill * fillSize);
                }

                component.setBounds(currentX, currentY, componentWidth, containerHeight);
                currentX += componentWidth;
                remainingSize -= componentWidth;
            } else {
                int componentHeight;
                if (constraint.getType() == ConstraintType.FIXED) {
                    componentHeight = constraint.getValue();
                } else {
                    componentHeight = (int) ((double) constraint.getValue() / totalFill * fillSize);
                }

                component.setBounds(currentX, currentY, containerWidth, componentHeight);
                currentY += componentHeight;
                remainingSize -= componentHeight;
            }
        }

        // give remaining size to last one
        if (remainingSize > 0) {
            for (int i = components.size() - 1; i >= 0; i--) {
                Component component = components.get(i);
                Constraint constraint = constraints.get(i);
                if (constraint.getType() == ConstraintType.FILL) {
                    if (isHorizontal) {
                        component.setSize(component.getWidth() + remainingSize, component.getHeight());
                    } else {
                        component.setSize(component.getWidth(), component.getHeight() + remainingSize);
                    }
                    break;
                }
            }
        }
    }
}