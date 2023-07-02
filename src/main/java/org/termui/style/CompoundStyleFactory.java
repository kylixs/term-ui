package org.termui.style;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author gongdewei 2023/7/2
 */
public class CompoundStyleFactory {

    // style cache map with key of hash of style, foregroundColor, backgroundColor
    private static Map<Integer, CompoundStyle> styleCache = new HashMap<>();

    public static CompoundStyle get(Style style, Color foregroundColor, Color backgroundColor) {
        int hash = Objects.hash(style, foregroundColor, backgroundColor);
        CompoundStyle compoundStyle = styleCache.get(hash);
        if (compoundStyle == null) {
            compoundStyle = new CompoundStyle(style, foregroundColor, backgroundColor);
            styleCache.put(hash, compoundStyle);
        }
        return compoundStyle;
    }
}
