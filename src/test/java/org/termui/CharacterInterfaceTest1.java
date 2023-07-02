package org.termui;

import java.util.ArrayList;
import java.util.List;

public class CharacterInterfaceTest1 {
    private char[][] mainBuffer;
    private List<Component> components;

    public CharacterInterfaceTest1(int width, int height) {
        mainBuffer = new char[height][width];
        components = new ArrayList<>();
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public void draw() {
        for (char[] row : mainBuffer) {
            for (int i = 0; i < row.length; i++) {
                row[i] = ' ';
            }
        }

        for (Component component : components) {
            component.draw(mainBuffer);
        }

        for (char[] row : mainBuffer) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CharacterInterfaceTest1 characterInterface = new CharacterInterfaceTest1(80, 24);

        Label label = new Label(10, 5, "Hello World");
        characterInterface.addComponent(label);

        Button button = new Button(30, 10, "Click Me");
        characterInterface.addComponent(button);

        TextField textField = new TextField(20, 15, 20);
        textField.setText("Type here");
        characterInterface.addComponent(textField);

        characterInterface.draw();
    }
}