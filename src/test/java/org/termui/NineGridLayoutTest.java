package org.termui;

import org.termui.border.BorderStyle;
import org.termui.layout.AdaptiveLayout;
import org.termui.layout.ConstraintType;
import org.termui.layout.HorizontalAlignment;
import org.termui.layout.NineGridLayoutManager;
import org.termui.layout.Position;
import org.termui.layout.VerticalAlignment;
import org.termui.layout.VerticalLayout;
import org.termui.style.Color;

public class NineGridLayoutTest {

    public static void main(String[] args) {
        Window window = new Window(80, 24);

        Panel panel = new Panel(10, 5, 60, 13);
        panel.setLayoutManager(new NineGridLayoutManager(panel));

        Label leftTopLabel = new Label("LEFT_TOP")
                .withBorder(BorderStyle.SINGLE_LINE)
                .withBackgroundColor(Color.BLUE);
        Label topLabel = new Label("TOP")
                .withBorder(BorderStyle.SINGLE_LINE)
                .withBackgroundColor(Color.RED);
        Label rightTopLabel = new Label("RIGHT_TOP").withBorder(BorderStyle.SINGLE_LINE);
        Label leftLabel = new Label("LEFT").withBorder(BorderStyle.SINGLE_LINE);
        Label centerLabel = new Label("CENTER")
                .withBorder(BorderStyle.SINGLE_LINE)
                .withForegroundColor(Color.MAGENTA)
                .withHorizontalAlignment(HorizontalAlignment.CENTER)
                .withVerticalAlignment(VerticalAlignment.MIDDLE);
        Label rightLabel = new Label("RIGHT").withBorder(BorderStyle.SINGLE_LINE);
        Label leftBottomLabel = new Label("LEFT_BOTTOM").withBorder(BorderStyle.SINGLE_LINE);
        Label bottomLabel = new Label("BOTTOM").withBorder(BorderStyle.SINGLE_LINE);
        Label rightBottomLabel = new Label("RIGHT_BOTTOM").withBorder(BorderStyle.SINGLE_LINE);

        Panel centerPanel = new Panel(0, 0, 10, 10)
                .withBorder(BorderStyle.SINGLE_LINE)
                .withBackgroundColor(Color.GREEN);
        // Compound AdaptiveLayout here
        AdaptiveLayout centerLayout = new AdaptiveLayout(false);
        centerPanel.setLayoutManager(centerLayout);
        centerPanel.addComponent(centerLabel);
        centerLayout.setConstraint(0, ConstraintType.FILL);

        topLabel.setAlignment(HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        //centerLabel.setAlignment(HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        centerPanel.setAlignment(HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
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
        //layoutManager.addComponent(centerLabel, Position.CENTER);
        layoutManager.addComponent(centerPanel, Position.CENTER);
        layoutManager.addComponent(rightLabel, Position.RIGHT);
        layoutManager.addComponent(leftBottomLabel, Position.LEFT_BOTTOM);
        layoutManager.addComponent(bottomLabel, Position.BOTTOM);
        layoutManager.addComponent(rightBottomLabel, Position.RIGHT_BOTTOM);

        Panel rootPanel = window.getRootPanel();
        rootPanel.setLayoutManager(new VerticalLayout());
        rootPanel.addComponent(panel);
        rootPanel.doLayout();

        window.draw();
    }
}