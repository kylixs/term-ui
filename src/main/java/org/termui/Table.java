package org.termui;

import java.util.ArrayList;
import java.util.List;

public class Table extends Component {
    private int numColumns;
    private int numRows;
    private List<Integer> columnWidths;
    private List<Boolean> stretchableColumns;
    private List<Boolean> stretchableRows;
    private List<String[]> data;

    public Table(int x, int y, int numColumns, int numRows) {
        super(x, y, 0, 0);
        this.numColumns = numColumns;
        this.numRows = numRows;
        this.columnWidths = new ArrayList<>();
        this.stretchableColumns = new ArrayList<>();
        this.stretchableRows = new ArrayList<>();
        this.data = new ArrayList<>();

        // 初始化列宽、拉伸属性和数据
        for (int i = 0; i < numColumns; i++) {
            columnWidths.add(10); // 默认列宽为10
            stretchableColumns.add(true); // 默认可拉伸
        }
        for (int i = 0; i < numRows; i++) {
            stretchableRows.add(true); // 默认可拉伸
            data.add(new String[numColumns]); // 初始化数据
        }
    }

    public void setColumnWidth(int columnIndex, int width) {
        if (columnIndex >= 0 && columnIndex < numColumns) {
            columnWidths.set(columnIndex, width);
        }
    }

    public void setColumnStretchable(int columnIndex, boolean stretchable) {
        if (columnIndex >= 0 && columnIndex < numColumns) {
            stretchableColumns.set(columnIndex, stretchable);
        }
    }

    public void setRowStretchable(int rowIndex, boolean stretchable) {
        if (rowIndex >= 0 && rowIndex < numRows) {
            stretchableRows.set(rowIndex, stretchable);
        }
    }

    public void setData(int rowIndex, int columnIndex, String value) {
        if (rowIndex >= 0 && rowIndex < numRows && columnIndex >= 0 && columnIndex < numColumns) {
            data.get(rowIndex)[columnIndex] = value;
        }
    }

    @Override
    public void draw(char[][] buffer) {
        super.draw(buffer);

        int x = getX();
        int y = getY();

        int[] columnWidthsArray = new int[numColumns];
        int totalWidth = 0;
        for (int i = 0; i < numColumns; i++) {
            columnWidthsArray[i] = columnWidths.get(i);
            totalWidth += columnWidthsArray[i];
        }

        int[] rowHeights = new int[numRows];
        for (int i = 0; i < numRows; i++) {
            rowHeights[i] = stretchableRows.get(i) ? 1 : 3; // 根据是否可拉伸设置行高
        }

        int tableWidth = totalWidth + numColumns + 1;
        int tableHeight = numRows * 3 + 1;

        int tableX = x;
        int tableY = y;

        // 绘制表格单元格边框
        for (int i = 0; i < numRows; i++) {
            int cellY = tableY + i * 3;
            for (int j = 0; j < numColumns; j++) {
                int cellX = tableX + j * (columnWidthsArray[j] + 1);
                int cellWidth = columnWidthsArray[j] + 1;
                int cellHeight = rowHeights[i];

                drawCellBorder(buffer, cellX, cellY, cellWidth, cellHeight);
            }
        }

        // 绘制表格内容
        for (int i = 0; i < numRows; i++) {
            String[] cols = data.get(i);
            for (int j = 0; j < numColumns; j++) {
                String value = cols[j];
                int cellX = tableX + j * (columnWidthsArray[j] + 1) + 1;
                int cellY = tableY + i * 3 + 1;

                int cellWidth = columnWidthsArray[j];
                int cellHeight = 3;

                drawCellContent(buffer, cellX, cellY, cellWidth, cellHeight, value);
            }
        }
    }

    private void drawCellBorder(char[][] buffer, int cellX, int cellY, int cellWidth, int cellHeight) {
        // 绘制单元格边框
        for (int i = 0; i < cellWidth; i++) {
            buffer[cellY][cellX + i] = '-';
            buffer[cellY + cellHeight - 1][cellX + i] = '-';
        }
        for (int i = 0; i < cellHeight; i++) {
            buffer[cellY + i][cellX] = '|';
            buffer[cellY + i][cellX + cellWidth - 1] = '|';
        }
        buffer[cellY][cellX] = '+';
        buffer[cellY][cellX + cellWidth - 1] = '+';
        buffer[cellY + cellHeight - 1][cellX] = '+';
        buffer[cellY + cellHeight - 1][cellX + cellWidth - 1] = '+';
    }

    private void drawCellContent(char[][] buffer, int cellX, int cellY, int cellWidth, int cellHeight, String value) {
        int textLength = Math.min(value.length(), cellWidth - 2); // 确保文本长度不超过单元格宽度-2

        // 绘制单元格内容
        for (int i = 0; i < textLength; i++) {
            buffer[cellY + 1][cellX + 1 + i] = value.charAt(i);
        }
    }
}

