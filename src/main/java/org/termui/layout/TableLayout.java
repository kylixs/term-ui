package org.termui.layout;

import org.termui.Component;
import org.termui.Panel;

import java.util.List;

public class TableLayout implements LayoutManager {
    private int rows;
    private int cols;

    public TableLayout(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public void layoutComponents(Panel container) {
        List<Component> components = container.getComponents();
        int x = container.getX();
        int y = container.getY();
        int containerWidth = container.getWidth();
        int containerHeight = container.getHeight();

        int cellWidth = containerWidth / cols;
        int cellHeight = containerHeight / rows;

        int currentX = x;
        int currentY = y;

        int componentIndex = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (componentIndex >= components.size()) {
                    return;
                }

                Component component = components.get(componentIndex);
                component.setBounds(currentX, currentY, cellWidth, cellHeight);

                currentX += cellWidth;

                componentIndex++;
            }

            currentX = x;
            currentY += cellHeight;
        }
    }
}