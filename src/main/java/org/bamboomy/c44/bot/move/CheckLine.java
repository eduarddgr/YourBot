
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
import org.bamboomy.c44.bot.player.Player;

public class CheckLine {

	private int xDelta, yDelta;
	private Board board;
	private LinePiece linePiece;

	private ArrayList<Place> oneDirection = new ArrayList<Place>(), otherDirection = new ArrayList<Place>();

	public CheckLine(int xDelta, int yDelta, Board board, LinePiece linePiece) {

		this.xDelta = xDelta;
		this.yDelta = yDelta;
		this.board = board;
		this.linePiece = linePiece;

		update();
	}

	public void update() {

		oneDirection = new ArrayList<Place>();

		Place otherPlace = linePiece.getCurrentPlace();

		while (otherPlace != null) {

			if (otherPlace.getX() + xDelta < 11 && otherPlace.getY() + yDelta < 11 && otherPlace.getY() + yDelta > 0) {

				if (otherPlace != linePiece.getCurrentPlace()) {

					oneDirection.add(otherPlace);
				}

				otherPlace = board.getPlacez()[otherPlace.getX() + xDelta][otherPlace.getY() + yDelta];

			} else {

				otherPlace = null;
			}
		}

		otherDirection = new ArrayList<Place>();

		otherPlace = linePiece.getCurrentPlace();

		while (otherPlace != null) {

			if (otherPlace.getX() - xDelta > 0 && otherPlace.getY() - yDelta < 11 && otherPlace.getY() - yDelta > 0) {

				if (otherPlace != linePiece.getCurrentPlace()) {

					otherDirection.add(otherPlace);
				}

				otherPlace = board.getPlacez()[otherPlace.getX() - xDelta][otherPlace.getY() - yDelta];

			} else {

				otherPlace = null;
			}
		}
	}

	public void checkCheck(Player player) {

		for (Place place : oneDirection) {

			if (place.getPiece() != null && place.getPiece() != player.getKing()) {

				break;
			}

			if (place.getPiece() == player.getKing()) {

				player.setCheck(linePiece);
			}
		}

		for (Place place : otherDirection) {

			if (place.getPiece() != null && place.getPiece() != player.getKing()) {

				break;
			}

			if (place.getPiece() == player.getKing()) {

				player.setCheck(linePiece);
			}
		}
	}

}
