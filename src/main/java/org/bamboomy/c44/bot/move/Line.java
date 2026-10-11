
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

package org.bamboomy.c44.bot.move;

import java.util.ArrayList;

import org.bamboomy.c44.bot.board.Board;
import org.bamboomy.c44.bot.board.Place;
import org.bamboomy.c44.bot.piecez.LinePiece;

public class Line {

	private final int xDelta, yDelta;

	private final Board board;

	private final LinePiece linePiece;

	private final ArrayList<Place> placez = new ArrayList<Place>();

	public Line(int xDelta, int yDelta, Board board, LinePiece linePiece) {

		this.xDelta = xDelta;
		this.yDelta = yDelta;

		this.board = board;

		this.linePiece = linePiece;
	}

	public void calculateRange(Place to) {

		placez.clear();

		Place currentPlace = linePiece.getCurrentPlace();
		Place nextPlace;

		int nextX = currentPlace.getX();
		int nextY = currentPlace.getY();

		boolean stopped = false;

		while (!stopped) {

			nextX = nextX + xDelta;
			nextY = nextY + yDelta;

			if (nextX > 11 || nextX < 0 || nextY > 11 || nextY < 0) {

				break;
			}

			nextPlace = board.getPlacez()[nextX][nextY];

			if (nextPlace != null && nextPlace != to) {

				placez.add(nextPlace);

				stopped = nextPlace.getPiece() != null;

			} else {

				stopped = true;
			}
		}

	}

	public boolean includes(Place to) {

		return placez.contains(to);
	}

}
