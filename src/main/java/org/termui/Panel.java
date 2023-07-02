package org.termui;

import org.termui.layout.LayoutManager;

import java.util.ArrayList;
import java.util.List;

public class Panel extends Component {
    private List<Component> components;
    private LayoutManager layoutManager;

    public Panel(int x, int y, int width, int height) {
        super(x, y, width, height);
        components = new ArrayList<>();
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    @Override
    public void draw(char[][] buffer) {
        super.draw(buffer);
        for (Component component : components) {
            component.draw(buffer);
        }
    }

    public void doLayout() {
        if (layoutManager != null) {
            layoutManager.layoutComponents(this);
            for (Component component : components) {
                if (component instanceof Panel) {
                    Panel nestedPanel = (Panel) component;
                    nestedPanel.doLayout();
                }
            }
        }
    }

    public LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public List<Component> getComponents() {
        return components;
    }
}
