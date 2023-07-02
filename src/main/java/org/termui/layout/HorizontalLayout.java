package org.termui.layout;

import org.termui.Component;
import org.termui.Panel;

import java.util.List;

public class HorizontalLayout implements LayoutManager {
    @Override
    public void layoutComponents(Panel container) {
        List<Component> components = container.getComponents();
        int x = container.getX();
        int y = container.getY();
        int containerHeight = container.getHeight();

        int currentX = x;
        for (Component component : components) {
            int componentWidth = component.getWidth();
            component.setBounds(currentX, y, componentWidth, containerHeight);
            currentX += componentWidth;
        }
    }
}