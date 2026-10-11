
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

import java.util.ArrayList;
import java.util.Stack;

import org.bamboomy.c44.bot.board.Board;
import org.bamboomy.c44.bot.board.Place;
import org.bamboomy.c44.bot.move.Move;
import org.bamboomy.c44.bot.player.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Piece {

	@Getter
	@Setter
	protected int color, oldColor;

	@Getter
	protected Player player;

	@Getter
	private final String identifier;

	@Getter
	@Setter
	protected Place currentPlace;

	@Getter
	private final String md5;

	@Getter
	protected ArrayList<Move> movez = new ArrayList<>();

	@Getter
	protected boolean pinned = false;

	protected boolean inited = false;

	protected ArrayList<Piece> capturedPieces = new ArrayList<>();

	@Getter
	@Setter
	protected boolean removed = false;

	@Getter
	protected final PieceValue value;

	@Getter
	protected Stack<Boolean> moved = new Stack<>();

	private static final boolean DEBUG = false;

	protected Board board;

	private int attached = 0;

	@Getter
	@Setter
	private ArrayList<Move> preventingMoves;

	public Piece(Place place, int color, Player player, String identifier, PieceValue value, boolean moved, Board board,
			String md5, int oldColor) {

		this.currentPlace = place;

		this.color = color;
		this.oldColor = oldColor;

		this.player = player;

		this.identifier = identifier;

		this.value = value;

		this.moved.push(moved);

		this.board = board;

		this.md5 = md5;

		if (player != null) {

			player.getPiecez().add(this);
		}
	}

	public void calculateMovez() {

		movez = new ArrayList<>();
	}

	public synchronized boolean handleOtherPlaceNE(Place otherPlace) {

		if (otherPlace != null && otherPlace.getPiece() != null) {

			if (DEBUG) {

				log.debug("attack (" + otherPlace.getPiece().getColor() + "(" + otherPlace.getPiece().getIdentifier()
						+ "))(" + color + ")");
			}

			/*
			 * 
			 * TODO: allicance
			 * 
			 * if (alliance != null) {
			 * 
			 * if (otherPlace.getPiece().getColor() != color &&
			 * otherPlace.getPiece().getColor() !=
			 * alliance.getOtherColor(Color.getBySeq(color)).getSeq()) {
			 * 
			 * movez.add(new Move(currentPlace, otherPlace, this)); }
			 * 
			 * } else {
			 * 
			 */

			if (otherPlace.getPiece().getColor() != color) {

				movez.add(new Move(currentPlace, otherPlace, this, "m"));
			}
			// }

			/*
			 * if (!isPinnable()) { //player.kingAttack(otherPlace); }
			 * 
			 */

			return true;

		} else if (otherPlace != null) {

			movez.add(new Move(currentPlace, otherPlace, this, "m"));

			if (DEBUG) {

				log.debug("continue");
			}

			// player.kingAttack(otherPlace);
		}

		return false;
	}

	public void setMovez(JSONArray jsonArray) {

		if (!jsonArray.isEmpty()) {

			System.out.println(jsonArray.toList().size());

			int size = jsonArray.toList().size();

			JSONArray jsonMovez = jsonArray;

			for (int i = 0; i < size; i++) {

				JSONObject from = jsonMovez.getJSONObject(i).getJSONObject("from");
				JSONObject to = jsonMovez.getJSONObject(i).getJSONObject("to");

				Move move = new Move(currentPlace, board.getPlacez()[to.getInt("x")][to.getInt("y")], this, "m");

				move.getTo().setMd5(to.getString("md5"));

				movez.add(move);

				System.out.println("(" + from.getInt("x") + ", " + from.getInt("y") + ") -> (" + to.getInt("x") + ", "
						+ to.getInt("y") + ")");
			}
		}
	}

	public boolean moveTo(Place placeTo, boolean unused) {

		currentPlace.setPiece(null);

		currentPlace = placeTo;

		if (currentPlace.getPiece() != null) {

			if (DEBUG) {

				log.debug("taken: (" + currentPlace.getPiece().getIdentifier() + ") -> (" + getIdentifier() + ")");
			}

			currentPlace.getPiece().setRemoved(true);

			capturedPieces.add(currentPlace.getPiece());
		}

		currentPlace.setPiece(this);

		moved.push(true);

		return true;
	}

	public void rollBackMoved() {

		moved.pop();
	}

	public abstract void propagateValues();

	protected boolean attachMe(Place otherPlace, Piece piece) {

		if (otherPlace == null) {

			return true;
		}

		if (DEBUG) {

			attached++;

			System.out.println(attached);
		}

		otherPlace.attach(piece);

		if (otherPlace.getPiece() != null) {

			return true;
		}

		return false;
	}

	public abstract void calculateDefence(Place to);

	public abstract void calculateOffence();

	public abstract boolean attacks(Place to);

	public abstract boolean defends(Place to);

	public abstract void updateChecks(Player player);

}
