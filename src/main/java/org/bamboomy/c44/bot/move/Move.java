
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

import org.bamboomy.c44.bot.board.Place;
import org.bamboomy.c44.bot.piecez.Piece;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Move {

	protected Place from, to;

	protected Piece piece;

	private Piece takenPiece;

	private static final boolean DEBUG = false;

	protected String identifier = "m";
	
	@Setter
	@Getter
	private Move[] movesToDepth;

	@Setter
	private Boolean attacked = null, defended = null;

	public Move(Place from, Place to, Piece piece, String identifier) {

		this.from = from;
		this.to = to;

		if (to == null) {

			throw new RuntimeException("to is null");
		}

		this.piece = piece;

		this.identifier = identifier;
	}

	public boolean execute(boolean fromGameMaster) {

		if (to.getPiece() != null) {

			takenPiece = to.getPiece();

			takenPiece.setRemoved(true);
		}

		return piece.moveTo(to, fromGameMaster);
	}

	public void rollback() {

		resetPiece();

		if (takenPiece != null) {

			to.setPiece(takenPiece);

			takenPiece.setRemoved(false);
		}

		/*
		 * if (enPassant != null) {
		 * 
		 * EnPassant result = enPassant.release();
		 * 
		 * enPassant = result;
		 * 
		 * piece.getPlayer().setEnPassant(result); }
		 */
	}

	protected void resetPiece() {

		piece.moveTo(from, false);

		piece.rollBackMoved();
	}

}
