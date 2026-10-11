
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

package org.bamboomy.c44.bot.board.gui;

import org.bamboomy.c44.bot.piecez.Piece;
import org.bamboomy.c44.bot.player.Color;

import lombok.Getter;

@Getter
public class GuiPiece {

	private final String identifier;

	private final int color;

	private String md5 = "md5";

	private boolean pinned = false;

	private boolean moved = false;

	public GuiPiece(Piece piece, String color, boolean currentPlayer) {

		identifier = piece.getIdentifier();
		this.color = piece.getColor();

		this.pinned = piece.isPinned();

		moved = piece.getMoved().get(0);

		if (currentPlayer && Color.getBySeq(piece.getColor()).getName().equalsIgnoreCase(color)) {

			this.md5 = piece.getMd5();
		}
	}
}
