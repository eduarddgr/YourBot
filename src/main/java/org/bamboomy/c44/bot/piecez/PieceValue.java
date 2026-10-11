
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

package org.bamboomy.c44.bot.piecez;

import lombok.Getter;

@Getter
public enum PieceValue {

	KING(0, "king", "k", 1000, King.class), PAWN(5, "pawn", "p", 1, Pawn.class),
	BISSHOP(3, "bisshop", "b", 3, Bisshop.class), TOWER(2, "tower", "t", 5, Tower.class),
	HORSE(4, "horse", "h", 3, Horse.class), QUEEN(1, "queen", "q", 10, Queen.class);

	private final int ordinal, value;
	private final String name, letter;
	private final Class pieceClass;

	private PieceValue(int ordinal, String name, String letter, int value, Class pieceClass) {
		this.ordinal = ordinal;
		this.name = name;
		this.letter = letter;
		this.value = value;
		this.pieceClass = pieceClass;
	}

	public static PieceValue getByName(String name) {

		for (PieceValue pv : PieceValue.values()) {

			if (pv.getName().equalsIgnoreCase(name)) {

				return pv;
			}
		}

		return null;
	}
}
