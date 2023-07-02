package org.termui.style;

public enum Style {
    BOLD(1),// 粗体
    ITALIC(3), // 斜体
    UNDERLINE(4), // 下划线
    STRIKETHROUGH(9), // 删除线
    RESET(0), // 重置
    HIDE(8), // 隐藏
    CLEAR(2); // 清除

    private int code;

    Style(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}