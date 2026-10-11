
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

import java.util.ArrayList;

import lombok.Getter;

public class Alliance {

	private Color one, two;

	public Alliance(Color one, Color two) {

		this.one = one;
		this.two = two;

		System.out.println("Alliance::" + one.getName() + ", " + two.getName());
	}

	public Color getOtherColor(Color color) {

		if (color.equals(one)) {

			return two;

		} else if (color.equals(two)) {

			return one;
		}

		throw new RuntimeException(color.getName() + " not part of this alliance!");
	}

	public boolean isInAlliance(Color color) {

		return color.equals(one) || color.equals(two);
	}

	public Alliance getOtherAlliance() {

		ArrayList<Color> otherColors = new ArrayList<Color>();

		for (Color color : Color.getAll()) {

			if (!isInAlliance(color)) {

				otherColors.add(color);
			}
		}

		return new Alliance(otherColors.get(0), otherColors.get(1));
	}

}
