package org.termui.style;

/**
 * @author gongdewei 2023/7/2
 */
public class StyleUtil {

    /**
     * <pre>
     * ANSI转义序列是一系列特殊的控制字符，可以用于在终端中设置文本的颜色、背景色、样式等。这些控制字符以\u001B开头，后跟一个或多个命令参数和终止字符。
     *
     * 以下是一些常用的ANSI转义序列命令：
     *
     * 设置文本颜色：\u001B[38;5;<color_code>m
     * 设置背景颜色：\u001B[48;5;<color_code>m
     * 设置文本样式：\u001B[<style_code>m
     * 其中，<color_code>代表颜色代码，可以是预定义的颜色代码或RGB值；<style_code>代表样式代码，可以是预定义的样式代码。
     * </pre>
     *
     * @param foregroundColor
     * @param backgroundColor
     * @param style
     * @return
     */
    public static String beginStyle(Color foregroundColor, Color backgroundColor, Style style) {
        StringBuilder sb = new StringBuilder();
        sb.append("\033["); // \u001B[
        boolean first = true;

        // 设置前景色
        if (foregroundColor != null && foregroundColor != Color.NONE) {
            first = false;
            sb.append(foregroundColor.getCode());
        }

        // 设置背景色
        if (backgroundColor != null && backgroundColor != Color.NONE) {
            if (!first) {
                sb.append(";");
            }
            first = false;
            sb.append(backgroundColor.getCode() + 10);
        }

        // 设置样式
        if (style != null && style != Style.NONE) {
            if (!first) {
                sb.append(";");
            }
            first = false;
            sb.append(style.getCode());
        }

        sb.append("m");
        return sb.toString();
    }

    public static String resetStyle() {
        return "\033[0m";
    }

    public static String beginStyle(CompoundStyle compoundStyle) {
        return beginStyle(compoundStyle.getForegroundColor(), compoundStyle.getBackgroundColor(), compoundStyle.getStyle());
    }
}
