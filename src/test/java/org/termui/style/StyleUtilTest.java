package org.termui.style;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author gongdewei 2023/7/2
 */
public class StyleUtilTest {
    @Test
    public void test() {
        String s = StyleUtil.beginStyle(Color.RED, Color.BLUE, Style.BOLD);
        System.out.println(s);
        System.out.println("Hello World");
        System.out.println(StyleUtil.resetStyle());

        Assert.assertEquals("\033[31;44;1m", s);
    }

    @Test
    public void testForeground() {
        String s = StyleUtil.beginStyle(Color.RED, null, Style.BOLD);
        System.out.println(s);
        System.out.println("Hello World");
        System.out.println(StyleUtil.resetStyle());

        Assert.assertEquals("\033[31;1m", s);
    }

    @Test
    public void testBackground() {
        String s = StyleUtil.beginStyle(null, Color.BLUE, Style.BOLD);
        System.out.println(s);
        System.out.println("Hello World");
        System.out.println(StyleUtil.resetStyle());

        Assert.assertEquals("\033[44;1m", s);
    }

    @Test
    public void testStyle() {
        String s = StyleUtil.beginStyle(null, null, Style.BOLD);
        System.out.println(s);
        System.out.println("Hello World");
        System.out.println(StyleUtil.resetStyle());

        Assert.assertEquals("\033[1m", s);
    }

    @Test
    public void testMultiWordsWithDifferentStyle() {
        String separator = StyleUtil.beginStyle(Color.YELLOW, Color.CYAN, null) + "|" + StyleUtil.resetStyle();

        String all = "";
        all += StyleUtil.beginStyle(Color.YELLOW, Color.BLUE, Style.BOLD);
        all += "Hello World";
        all += StyleUtil.resetStyle();
        all += separator;

        all +=  StyleUtil.beginStyle(Color.RED, Color.CYAN, Style.ITALIC);
        all += "Hello World";
        all += StyleUtil.resetStyle();
        all += separator;

        all +=  StyleUtil.beginStyle(Color.WHITE, Color.BLACK, Style.STRIKETHROUGH);
        all += "Hello World";
        all += StyleUtil.resetStyle();
        all += separator;

        all +=  StyleUtil.beginStyle(null, null, Style.UNDERLINE);
        all += "Hello World";
        all += StyleUtil.resetStyle();
        all += separator;

        System.out.println(all);
        String exptect = "\u001B[33;44;1mHello World\u001B[0m\u001B[33;46m|\u001B[0m\u001B[31;46;3mHello World\u001B[0m\u001B[33;46m|\u001B[0m\u001B[37;40;9mHello World\u001B[0m\u001B[33;46m|\u001B[0m\u001B[4mHello World\u001B[0m\u001B[33;46m|\u001B[0m";
        Assert.assertEquals( exptect, all);
    }


}
