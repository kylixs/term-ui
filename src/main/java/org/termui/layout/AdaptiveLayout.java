package org.termui.layout;

import org.termui.Component;
import org.termui.Panel;

import java.util.ArrayList;
import java.util.List;

public class AdaptiveLayout implements LayoutManager {
    private boolean isHorizontal;
    private List<Constraint> constraints;

    public AdaptiveLayout(boolean isHorizontal, List<Constraint> constraints) {
        this.isHorizontal = isHorizontal;
        this.constraints = constraints;
    }

    public AdaptiveLayout(boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
        constraints = new ArrayList<>();
    }

    // set constraint of component by index
    public void setConstraint(int index, ConstraintType type) {
        if (index >= constraints.size()) {
            for (int i = constraints.size(); i <= index; i++) {
                constraints.add(new Constraint(0, ConstraintType.FIXED));
            }
        }
        constraints.set(index, new Constraint(index, type));
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

        int numFillComponents = 0;
        for (Constraint constraint : constraints) {
            if (constraint.getType() == ConstraintType.FIXED) {
                totalFixed += constraint.getValue();
            } else if (constraint.getType() == ConstraintType.FILL) {
                totalFill += constraint.getValue();
                numFillComponents++;
            }
        }

        int fixedSize = isHorizontal ? containerWidth - totalFill : containerHeight - totalFill;
        int fillSize = isHorizontal ? containerWidth - totalFixed : containerHeight - totalFixed;
        int remainingSize = fillSize;

        int currentX = containerX;
        int currentY = containerY;
        int currentFillComponent = 0;

        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            Constraint constraint = constraints.get(i);

            if (isHorizontal) {
                int componentWidth;
                if (constraint.getType() == ConstraintType.FIXED) {
                    componentWidth = constraint.getValue();
                } else {
                    componentWidth = (int) ((double) constraint.getValue() / totalFill * fillSize);
                    currentFillComponent++;
                    remainingSize -= componentWidth;
                    // give remaining size to last one
                    if (currentFillComponent == numFillComponents) {
                        componentWidth += remainingSize;
                        remainingSize = 0;
                    }
                }

                component.setBounds(currentX, currentY, componentWidth, containerHeight);
                currentX += componentWidth;
            } else {
                int componentHeight;
                if (constraint.getType() == ConstraintType.FIXED) {
                    componentHeight = constraint.getValue();
                } else {
                    componentHeight = (int) ((double) constraint.getValue() / totalFill * fillSize);
                    currentFillComponent++;
                    remainingSize -= componentHeight;
                    // give remaining size to last one
                    if (currentFillComponent == numFillComponents) {
                        componentHeight += remainingSize;
                        remainingSize = 0;
                    }
                }

                component.setBounds(currentX, currentY, containerWidth, componentHeight);
                currentY += componentHeight;
            }
        }
    }
}