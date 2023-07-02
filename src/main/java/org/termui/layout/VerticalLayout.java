package org.termui.layout;

import org.termui.Component;
import org.termui.Panel;

import java.util.List;

public class VerticalLayout implements LayoutManager {
    @Override
    public void layoutComponents(Panel container) {
        List<Component> components = container.getComponents();
        int x = container.getX();
        int y = container.getY();
        int containerWidth = container.getWidth();

        int currentY = y;
        for (Component component : components) {
            int componentHeight = component.getHeight();
            component.setBounds(x, currentY, containerWidth, componentHeight);
            currentY += componentHeight;
        }
    }
}
