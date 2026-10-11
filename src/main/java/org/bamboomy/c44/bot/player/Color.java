
/**

This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or
distribute this software, either in source code form or as a compiled
binary, for any purpose, commercial or non-commercial, and by any
means.

In jurisdictions that recognize copyright laws, the author or authors
of this software dedicate any and all copyright interest in the
software to the public domain. We make this dedication for the benefit
of the public at large and to the detriment of our heirs and
successors. We intend this dedication to be an overt act of
relinquishment in perpetuity of all present and future rights to this
software under copyright law.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

For more information, please refer to <https://unlicense.org/>

**/


package org.bamboomy.c44.bot.player;

import lombok.Getter;

import java.util.function.BiFunction;

@Getter
public enum Color {

    GREEN(0, "Green", "green", 'g', 1, 0, (x, y) -> 11 - x, (x, y) -> 11 - y),
    BLUE(1, "Blue", "blue", 'b', 0, -1, (x, y) -> 11 - y, (x, y) -> x),
    RED(2, "Red", "red", 'r', -1, 0, (x, y) -> x, (x, y) -> y),
    YELLOW(3, "Yellow", "yellow", 'y', 0, 1, (x, y) -> y, (x, y) -> 11 - x),
    DEAD(4, "Dead", "grey", 'd', 0, 0, (x, y) -> x, (x, y) -> y),
    NO_COLOR(-1, "", "", 'n', 0, 0, (x, y) -> x, (x, y) -> y);

    private final int seq, xDirection, yDirection;
    private final String name;
    private final String cssColor;
    private final char shortDebug;
    private final BiFunction<Integer, Integer, Integer> xMapping;
    private final BiFunction<Integer, Integer, Integer> yMapping;

    Color(int seq, String name, String cssColor, char shortDebug,
          int xDirection, int yDirection,
          BiFunction<Integer, Integer, Integer> xMapping, BiFunction<Integer, Integer, Integer> yMapping) {
        this.seq = seq;
        this.name = name;
        this.cssColor = cssColor;
        this.shortDebug = shortDebug;
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.xMapping = xMapping;
        this.yMapping = yMapping;
    }

    public static Color getByName(String name) {

        for (Color p : Color.values()) {

            if (p.name.equalsIgnoreCase(name)) {

                return p;
            }
        }

        return null;
    }

    public static Color getBySeq(int seq) {

        for (Color p : Color.values()) {

            if (p.seq == seq) {

                return p;
            }
        }

        return null;
    }

    public static Color[] getAll() {

        Color[] colorz = new Color[4];

        for (int i = 0; i < 4; i++) {

            colorz[i] = Color.getBySeq(i);
        }

        return colorz;
    }

}
