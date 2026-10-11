
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

package org.bamboomy.c44.rest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.bamboomy.c44.Application;
import org.bamboomy.c44.bot.board.Board;
import org.bamboomy.c44.bot.player.Alliance;
import org.bamboomy.c44.bot.player.Color;
import org.bamboomy.c44.bot.player.Player;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Poller implements Runnable {

	private String serverUrl;
	private String robotHash;
	private String ownColor;

	private Alliance alliance;

	private Player player;

	private boolean thinking = false;

	private static final boolean OUTPUT_POUNDER = true;

	private ArrayList<String> colorHashes;

	public Poller(String serverUrl, ArrayList<String> colorHashes) {

		this.serverUrl = serverUrl;
		this.colorHashes = colorHashes;
	}

	@Override
	public void run() {

		boolean done = false;

		System.out.println(ownColor);

		System.out.print("waiting...");

		Board board = null;

		int index = 0;

		for (String hash : colorHashes) {

			sendName(hash);
		}

		while (!done) {

			System.out.print(".");

			try {

				Thread.sleep(3_000);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			StringBuffer robotBoard = getPath(serverUrl, "/getRobotBoard/" + colorHashes.get(index));

			int augment = 0;

			while (robotBoard == null && augment < colorHashes.size()) {

				System.out.println("gotten null response, trying with other color...");

				index = (index + 1) % colorHashes.size();

				augment++;

				robotBoard = getPath(serverUrl, "/getRobotBoard/" + colorHashes.get(index));
			}

			if (robotBoard != null && !robotBoard.toString().isEmpty()) {

				System.out.println("I'm supposed to play now...");

				ownColor = getPath(serverUrl, "/getColor/" + colorHashes.get(index)).toString();

				String allianceColor = getPath(serverUrl, "/getAllianceColor/" + colorHashes.get(index)).toString();

				alliance = new Alliance(Color.getByName(ownColor), Color.getByName(allianceColor));

				System.out.println("(with color: " + ownColor + "; in alliance: " + allianceColor + ")...");

				board = new Board(robotBoard.toString(), alliance, ownColor, this);

				player = board.getOwnPlayer();

				player.startCalculation();

				thinking = true;
			}

			long start = System.currentTimeMillis();

			int pointz = 0, meta = 0;

			long finish;

			robotHash = colorHashes.get(index);

			while (thinking) {

				if (OUTPUT_POUNDER) {

					System.out.print("Poundering[" + meta + "]...");

					for (int i = 0; i < pointz; i++) {

						System.out.print(".");
					}

					finish = System.currentTimeMillis();

					System.out.println("(" + (finish - start) / 1_000 + "s)");
				}

				try {

					Thread.sleep(7_000);

				} catch (InterruptedException e) {

					e.printStackTrace();
				}

				pointz++;

				if (pointz % 10 == 0) {

					meta++;
					pointz = 0;
				}
			}

			finish = System.currentTimeMillis();

			System.out.println("I poundered for: " + (finish - start) / 1_000 + " secondz...");

			index = (index + 1) % colorHashes.size();

			System.out.print("waiting to play again...");
		}

		System.out.println("I'm done...");
	}

	private StringBuffer getPath(String serverUrl, String path) {

		StringBuffer result = null;

		URL url;

		try {

			url = new URL(serverUrl + "/react" + path);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			result = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				result.append(inputLine);
			}
			in.close();

			con.disconnect();

			System.out.print(result.toString());

		} catch (ConnectException e) {

			System.out.println("could not connect :(");

			return null;

		} catch (IOException e) {

			e.printStackTrace();
		}

		return result;
	}

	public void movePiece(String pieceMd5, String moveMd5) {

		getPath(serverUrl, "/playBot/" + robotHash + "/" + pieceMd5 + "/" + moveMd5);

		System.out.println("moving piece...");

		thinking = false;
	}

	private void post(String output, String contentType, String urlPart, String hash) {

		StringBuffer result = null;

		URL url;

		try {

			String givenHash = robotHash;

			if (hash != null) {

				givenHash = hash;
			}

			url = new URL(serverUrl + "/react/" + urlPart + "/" + givenHash);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type", contentType);
			con.connect();

			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
			out.write(output);
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			result = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				result.append(inputLine);
			}

			in.close();

			con.disconnect();

			System.out.print(result.toString() + " -- ");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void output(String output) {

		post(output, "text/html; charset=utf-8", "updateOutput", null);
	}

	public void sendName(String hash) {

		post(Application.BOT_NAME, "text/html; charset=utf-8", "getName", hash);
	}

	public void sendBoard(Board board) {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = "doesn't work :(";

		try {

			json = ow.writeValueAsString(board.getGuiArray(ownColor, true));

		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		post(json, "application/json", "postBoard", null);
	}

	public void exception(String stack) {

		post(stack, "text/html; charset=utf-8", "exception", null);
	}
}
