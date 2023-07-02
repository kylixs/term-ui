package org.termui.layout;

import org.termui.Component;
import org.termui.Panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * +----+-------+----+
 * | LT |  TOP  | RT |
 * +----+-------+----+
 * | L  | CENTER|  R |
 * +----+-------+----+
 * | LB | BOTTOM| RB |
 * +----+-------+----+
 */
public class NineGridLayoutManager implements LayoutManager {
    private Map<Component, Position> componentPositionMap;
    private Panel panel;

    public NineGridLayoutManager(Panel panel) {
        this.panel = panel;
        componentPositionMap = new HashMap<>();
    }

    public Panel getContainer() {
        return panel;
    }

    public void setContainer(Panel panel) {
        this.panel = panel;
    }

    public void addComponent(Component component, Position position) {
        this.panel.addComponent(component);
        componentPositionMap.put(component, position);
    }

    @Override
    public void layoutComponents(Panel container) {
        int x = container.getX();
        int y = container.getY();
        int containerWidth = container.getWidth();
        int containerHeight = container.getHeight();

        Map<Position, Component> occupiedPositions = new HashMap<>();

        // Separate components into specified positions and empty positions
        List<Component> specifiedPositions = new ArrayList<>();
        List<Component> emptyPositions = new ArrayList<>();

        for (Component component : container.getComponents()) {
            Position position = componentPositionMap.get(component);
            if (position != null) {
                occupiedPositions.put(position, component);
                specifiedPositions.add(component);
            } else {
                emptyPositions.add(component);
            }
        }

        // Calculate the dimensions of each region
        int leftWidth = 0;
        int rightWidth = 0;
        int topHeight = 0;
        int bottomHeight = 0;
        int centerWidth = containerWidth;
        int centerHeight = containerHeight;

        // Calculate left and right widths
        for (Component component : specifiedPositions) {
            Position position = componentPositionMap.get(component);
            if (position == Position.LEFT || position == Position.LEFT_TOP || position == Position.LEFT_BOTTOM) {
                leftWidth = Math.max(leftWidth, component.getActualWidth());
            } else if (position == Position.RIGHT || position == Position.RIGHT_TOP || position == Position.RIGHT_BOTTOM) {
                rightWidth = Math.max(rightWidth, component.getActualWidth());
            }
        }

        // Calculate top and bottom heights
        for (Component component : specifiedPositions) {
            Position position = componentPositionMap.get(component);
            if (position == Position.TOP || position == Position.LEFT_TOP || position == Position.RIGHT_TOP) {
                topHeight = Math.max(topHeight, component.getActualHeight());
            } else if (position == Position.BOTTOM || position == Position.LEFT_BOTTOM || position == Position.RIGHT_BOTTOM) {
                bottomHeight = Math.max(bottomHeight, component.getActualHeight());
            }
        }

        // Calculate center dimensions
        centerWidth -= leftWidth + rightWidth;
        centerHeight -= topHeight + bottomHeight;

        // Layout specified positions
        for (Component component : specifiedPositions) {
            Position position = componentPositionMap.get(component);
            int componentX = 0;
            int componentY = 0;
            int componentHeight = component.getHeight();
            int componentWidth = component.getWidth();

            if (position == Position.LEFT_TOP || position == Position.LEFT || position == Position.LEFT_BOTTOM) {
                componentX = 0;
            } else if (position == Position.TOP || position == Position.CENTER || position == Position.BOTTOM) {
                componentX = leftWidth;
            } else if (position == Position.RIGHT_TOP || position == Position.RIGHT || position == Position.RIGHT_BOTTOM) {
                componentX = containerWidth - rightWidth;
            }

            if (position == Position.LEFT_TOP || position == Position.TOP || position == Position.RIGHT_TOP) {
                componentY = 0;
            } else if (position == Position.LEFT || position == Position.CENTER || position == Position.RIGHT) {
                // left 和 right 的高度应该拉伸占满空闲空间, 所以这里的 y 应该是 topHeight
                componentY = topHeight;
                componentHeight = centerHeight;
            } else if (position == Position.LEFT_BOTTOM || position == Position.BOTTOM || position == Position.RIGHT_BOTTOM) {
                componentY = containerHeight - bottomHeight;
            }

            // set width of middle components
            if (position == Position.TOP || position == Position.CENTER || position == Position.BOTTOM) {
                componentWidth = centerWidth;
            }

            // set width of left components
            if (position == Position.LEFT || position == Position.LEFT_TOP || position == Position.LEFT_BOTTOM) {
                componentWidth = leftWidth;
            }

            // set width of right components
            if (position == Position.RIGHT || position == Position.RIGHT_TOP || position == Position.RIGHT_BOTTOM) {
                componentWidth = rightWidth;
            }

            component.setBounds(componentX, componentY, componentWidth, componentHeight);
        }

        // Calculate remaining empty space for empty positions
        int emptyWidth = Math.max(centerWidth, 0);
        int emptyHeight = Math.max(centerHeight, 0);

        // Layout empty positions
        for (Component component : emptyPositions) {
            component.setBounds(x + leftWidth, y + topHeight, emptyWidth, emptyHeight);
        }
    }
}
