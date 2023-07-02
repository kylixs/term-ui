package org.termui.style;

public enum Style {
    NONE(-1), // 无样式(默认
    RESET(0), // 重置
    BOLD(1),// 粗体
    CLEAR(2), // 清除
    ITALIC(3), // 斜体
    UNDERLINE(4), // 下划线
    INVERSE(7), // 反转
    HIDE(8), // 隐藏
    STRIKETHROUGH(9); // 删除线

    private int code;

    Style(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}