package org.termui;

import org.termui.layout.HorizontalAlignment;
import org.termui.layout.NineGridLayoutManager;
import org.termui.layout.Position;
import org.termui.layout.VerticalAlignment;
import org.termui.layout.VerticalLayout;

public class NineGridLayoutTest {
    private char[][] mainBuffer;
    private Panel rootPanel;

    public NineGridLayoutTest(int width, int height) {
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
        NineGridLayoutTest characterInterface = new NineGridLayoutTest(80, 24);

        Panel panel = new Panel(10, 5, 60, 13);
        panel.setLayoutManager(new NineGridLayoutManager(panel));

        Label leftTopLabel = new Label("LEFT_TOP");
        Label topLabel = new Label("TOP");
        Label rightTopLabel = new Label("RIGHT_TOP");
        Label leftLabel = new Label("LEFT");
        Label centerLabel = new Label("CENTER");
        Label rightLabel = new Label("RIGHT");
        Label leftBottomLabel = new Label("LEFT_BOTTOM");
        Label bottomLabel = new Label("BOTTOM");
        Label rightBottomLabel = new Label("RIGHT_BOTTOM");

        topLabel.setAlignment(HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        centerLabel.setAlignment(HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        bottomLabel.setAlignment(HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);

        leftLabel.setAlignment(HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE);

        rightTopLabel.setAlignment(HorizontalAlignment.RIGHT, VerticalAlignment.TOP);
        rightLabel.setAlignment(HorizontalAlignment.RIGHT, VerticalAlignment.MIDDLE);
        rightBottomLabel.setAlignment(HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM);

        NineGridLayoutManager layoutManager = (NineGridLayoutManager) panel.getLayoutManager();
        layoutManager.addComponent(leftTopLabel, Position.LEFT_TOP);
        layoutManager.addComponent(topLabel, Position.TOP);
        layoutManager.addComponent(rightTopLabel, Position.RIGHT_TOP);
        layoutManager.addComponent(leftLabel, Position.LEFT);
        layoutManager.addComponent(centerLabel, Position.CENTER);
        layoutManager.addComponent(rightLabel, Position.RIGHT);
        layoutManager.addComponent(leftBottomLabel, Position.LEFT_BOTTOM);
        layoutManager.addComponent(bottomLabel, Position.BOTTOM);
        layoutManager.addComponent(rightBottomLabel, Position.RIGHT_BOTTOM);

        Panel rootPanel = new Panel(0, 0, 80, 24);
        rootPanel.setLayoutManager(new VerticalLayout());
        rootPanel.addComponent(panel);

        characterInterface.setRootContainer(rootPanel);
        rootPanel.doLayout();

        characterInterface.draw();
    }
}