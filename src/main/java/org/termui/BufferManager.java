package org.termui;
import java.util.ArrayList;
import java.util.List;

public class BufferManager {
    private final int width;
    private final int height;
    private char[][] mainBuffer; // 主缓冲区
    private char[][] inputBuffer; // 输入缓冲区
    private List<char[][]> temporaryBuffers; // 临时缓冲区列表

    public BufferManager(int width, int height) {
        this.width = width;
        this.height = height;
        mainBuffer = new char[height][width];
        inputBuffer = new char[height][width];
        temporaryBuffers = new ArrayList<>();

        // 初始化主缓冲区和输入缓冲区为全空格
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mainBuffer[i][j] = ' ';
                inputBuffer[i][j] = ' ';
            }
        }
    }

    public void drawWindow(char[][] window, int x, int y) {
        int height = window.length;
        int width = window[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (x + j >= 0 && x + j < mainBuffer[0].length && y + i >= 0 && y + i < mainBuffer.length) {
                    if (window[i][j] != ' ') {
                        mainBuffer[y + i][x + j] = window[i][j];
                    }
                }
            }
        }
    }

    public void drawMainBuffer() {
        for (char[] row : mainBuffer) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public void createTemporaryBuffer(int width, int height) {
        char[][] temporaryBuffer = new char[height][width];
        temporaryBuffers.add(temporaryBuffer);
    }

    public void drawTemporaryBuffer(int index) {
        if (index >= 0 && index < temporaryBuffers.size()) {
            char[][] temporaryBuffer = temporaryBuffers.get(index);
            for (char[] row : temporaryBuffer) {
                for (char c : row) {
                    System.out.print(c);
                }
                System.out.println();
            }
        }
    }

    public void drawString(String str, int x, int y) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (x + i >= 0 && x + i < mainBuffer[0].length && y >= 0 && y < mainBuffer.length) {
                mainBuffer[y][x + i] = str.charAt(i);
            }
        }
    }

    public void drawStringAtCorner(String str, Corner corner) {
        int length = str.length();
        int x, y;

        switch (corner) {
            case TOP_LEFT:
                x = 0;
                y = 0;
                break;
            case TOP_RIGHT:
                x = mainBuffer[0].length - length;
                y = 0;
                break;
            case BOTTOM_LEFT:
                x = 0;
                y = mainBuffer.length - 1;
                break;
            case BOTTOM_RIGHT:
                x = mainBuffer[0].length - length;
                y = mainBuffer.length - 1;
                break;
            default:
                return;
        }

        for (int i = 0; i < length; i++) {
            if (x + i >= 0 && x + i < mainBuffer[0].length && y >= 0 && y < mainBuffer.length) {
                mainBuffer[y][x + i] = str.charAt(i);
            }
        }
    }

    public void drawStringCentered(String str, int row) {
        int length = str.length();
        int x = (mainBuffer[0].length - length) / 2;
        int y = row;

        for (int i = 0; i < length; i++) {
            if (x + i >= 0 && x + i < mainBuffer[0].length && y >= 0 && y < mainBuffer.length) {
                mainBuffer[y][x + i] = str.charAt(i);
            }
        }
    }

    public static void main(String[] args) {
        BufferManager bufferManager = new BufferManager(80, 14);

        bufferManager.drawStringCentered("The Middle", bufferManager.height/2);

        bufferManager.drawStringAtCorner("Top Left", Corner.TOP_LEFT);
        bufferManager.drawStringAtCorner("Top Right", Corner.TOP_RIGHT);
        bufferManager.drawStringAtCorner("Bottom Left", Corner.BOTTOM_LEFT);
        bufferManager.drawStringAtCorner("Bottom Right", Corner.BOTTOM_RIGHT);
        bufferManager.drawMainBuffer();
    }
}
