package org.termui;

import org.termui.style.Color;
import org.termui.style.Style;

public class FixedLayoutTest {

    public static void main(String[] args) {

        Window window = new Window(80, 24);
        Panel rootPanel = window.getRootPanel();

        Label label = new Label(10, 5, "Hello World")
                .withForegroundColor(Color.BLUE)
                .withBackgroundColor(Color.WHITE);
        rootPanel.addComponent(label);

        Button button = new Button(30, 10, "Click Me")
                .withBackgroundColor(Color.RED)
                .withStyle(Style.BOLD);
        rootPanel.addComponent(button);

        TextField textField = new TextField(20, 15, 20)
                .withBackgroundColor(Color.WHITE)
                .withForegroundColor(Color.BLACK);
        textField.setText("Type here");
        rootPanel.addComponent(textField);

        window.draw();
    }
}