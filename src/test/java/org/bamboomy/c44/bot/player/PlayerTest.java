package org.bamboomy.c44.bot.player;

import org.bamboomy.c44.bot.board.Board;
import org.bamboomy.c44.rest.Poller;
import org.easymock.Capture;
import org.easymock.CaptureType;
import org.easymock.EasyMock;
import org.junit.Ignore;
import org.junit.Test;

public class PlayerTest {

	private Poller pollerMock = EasyMock.mock(Poller.class);

	Capture<String> piece = Capture.newInstance(CaptureType.ALL);
	Capture<String> place = Capture.newInstance(CaptureType.ALL);

	private String json = """
			[
			  [
			    null,
			    null,
			    {
			      "guiPiece": {
			        "color": 0,
			        "identifier": "t",
			        "oldColor": 0,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 0,
			      "y": 2,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 0,
			        "identifier": "h",
			        "oldColor": 0,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 0,
			      "y": 3,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 0,
			      "y": 4,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 0,
			        "identifier": "k",
			        "oldColor": 0,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 0,
			      "y": 5,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 0,
			      "y": 6,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 0,
			        "identifier": "b",
			        "oldColor": 0,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 0,
			      "y": 7,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 0,
			        "identifier": "h",
			        "oldColor": 0,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 0,
			      "y": 8,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 0,
			        "identifier": "t",
			        "oldColor": 0,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 0,
			      "y": 9,
			      "md5": "md5"
			    },
			    null,
			    null
			  ],
			  [
			    null,
			    null,
			    {
			      "guiPiece": {
			        "color": 0,
			        "identifier": "p",
			        "oldColor": 0,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 1,
			      "y": 2,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 0,
			        "identifier": "p",
			        "oldColor": 0,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 1,
			      "y": 3,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 0,
			        "identifier": "p",
			        "oldColor": 0,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 1,
			      "y": 4,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 1,
			      "y": 5,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 0,
			        "identifier": "p",
			        "oldColor": 0,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 1,
			      "y": 6,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 1,
			      "y": 7,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 0,
			        "identifier": "p",
			        "oldColor": 0,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 1,
			      "y": 8,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 1,
			      "y": 9,
			      "md5": "md5"
			    },
			    null,
			    null
			  ],
			  [
			    {
			      "guiPiece": {
			        "color": 3,
			        "identifier": "t",
			        "oldColor": 3,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 2,
			      "y": 0,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 3,
			        "identifier": "p",
			        "oldColor": 3,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 2,
			      "y": 1,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 2,
			      "y": 2,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 2,
			      "y": 3,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 2,
			      "y": 4,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 2,
			      "y": 5,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 2,
			      "y": 6,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 0,
			        "identifier": "p",
			        "oldColor": 0,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 2,
			      "y": 7,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 2,
			      "y": 8,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 2,
			      "y": 9,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 2,
			      "y": 10,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 4,
			        "identifier": "t",
			        "oldColor": 1,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 2,
			      "y": 11,
			      "md5": "md5"
			    }
			  ],
			  [
			    {
			      "guiPiece": null,
			      "x": 3,
			      "y": 0,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 3,
			        "identifier": "p",
			        "oldColor": 3,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 3,
			      "y": 1,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 3,
			      "y": 2,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 3,
			      "y": 3,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 3,
			      "y": 4,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 0,
			        "identifier": "p",
			        "oldColor": 0,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 3,
			      "y": 5,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 3,
			      "y": 6,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 0,
			        "identifier": "b",
			        "oldColor": 0,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 3,
			      "y": 7,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 0,
			        "identifier": "p",
			        "oldColor": 0,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 3,
			      "y": 8,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 3,
			      "y": 9,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 3,
			      "y": 10,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 4,
			        "identifier": "h",
			        "oldColor": 1,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 3,
			      "y": 11,
			      "md5": "md5"
			    }
			  ],
			  [
			    {
			      "guiPiece": {
			        "color": 3,
			        "identifier": "b",
			        "oldColor": 3,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 4,
			      "y": 0,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 3,
			        "identifier": "p",
			        "oldColor": 3,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 4,
			      "y": 1,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 3,
			        "identifier": "h",
			        "oldColor": 3,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 4,
			      "y": 2,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 4,
			      "y": 3,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 4,
			      "y": 4,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 4,
			      "y": 5,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 4,
			      "y": 6,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 4,
			      "y": 7,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 4,
			      "y": 8,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 4,
			        "identifier": "p",
			        "oldColor": 1,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 4,
			      "y": 9,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 4,
			      "y": 10,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 4,
			        "identifier": "b",
			        "oldColor": 1,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 4,
			      "y": 11,
			      "md5": "md5"
			    }
			  ],
			  [
			    {
			      "guiPiece": {
			        "color": 3,
			        "identifier": "q",
			        "oldColor": 3,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 5,
			      "y": 0,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 3,
			        "identifier": "p",
			        "oldColor": 3,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 5,
			      "y": 1,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 5,
			      "y": 2,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 5,
			      "y": 3,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 5,
			      "y": 4,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 5,
			      "y": 5,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 5,
			      "y": 6,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 5,
			      "y": 7,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 5,
			      "y": 8,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 5,
			      "y": 9,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 4,
			        "identifier": "p",
			        "oldColor": 1,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 5,
			      "y": 10,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 4,
			        "identifier": "q",
			        "oldColor": 1,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 5,
			      "y": 11,
			      "md5": "md5"
			    }
			  ],
			  [
			    {
			      "guiPiece": null,
			      "x": 6,
			      "y": 0,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 3,
			        "identifier": "p",
			        "oldColor": 3,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 6,
			      "y": 1,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 6,
			      "y": 2,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 6,
			      "y": 3,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 6,
			      "y": 4,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 6,
			      "y": 5,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 6,
			      "y": 6,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 6,
			      "y": 7,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 0,
			        "identifier": "q",
			        "oldColor": 0,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 6,
			      "y": 8,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 6,
			      "y": 9,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 4,
			        "identifier": "p",
			        "oldColor": 1,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 6,
			      "y": 10,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 6,
			      "y": 11,
			      "md5": "md5"
			    }
			  ],
			  [
			    {
			      "guiPiece": {
			        "color": 3,
			        "identifier": "b",
			        "oldColor": 3,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 7,
			      "y": 0,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 7,
			      "y": 1,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 7,
			      "y": 2,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 3,
			        "identifier": "k",
			        "oldColor": 3,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 7,
			      "y": 3,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 7,
			      "y": 4,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 7,
			      "y": 5,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 7,
			      "y": 6,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 7,
			      "y": 7,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 7,
			      "y": 8,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 4,
			        "identifier": "p",
			        "oldColor": 1,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 7,
			      "y": 9,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 7,
			      "y": 10,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 4,
			        "identifier": "b",
			        "oldColor": 1,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 7,
			      "y": 11,
			      "md5": "md5"
			    }
			  ],
			  [
			    {
			      "guiPiece": {
			        "color": 3,
			        "identifier": "h",
			        "oldColor": 3,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 8,
			      "y": 0,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 3,
			        "identifier": "p",
			        "oldColor": 3,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 8,
			      "y": 1,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 2,
			        "identifier": "t",
			        "oldColor": 2,
			        "md5": "7E0705287B337D586EBC4F8EB47245D2",
			        "movez": [
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 2,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 9,
			              "y": 2,
			              "md5": "1FFFB37DD81CA2B8523A1AA634663005"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 2,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 2,
			              "md5": "2F8CAE39A5B16942EBD2B373070EB4B3"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 2,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 2,
			              "md5": "44A8DB5BBFF792E741D95DF1981892E2"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 2,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 3,
			              "md5": "A074F8E676B6D744481244094123352E"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 2,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 4,
			              "md5": "0545077C525146880C9AA3B4518E3B78"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 2,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 5,
			              "md5": "B802C844E146FD29A24C6BA58988B06B"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 2,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 6,
			              "md5": "BA7359B2D417527B104BD61BBC58D254"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 2,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 7,
			              "md5": "B601C84F49FCCECA99007F1B64265163"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 2,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 8,
			              "md5": "E465D9C47B99DF88CFCFCEB552D7218C"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 2,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 9,
			              "md5": "9E069DFB39502A0156D5B4DFD7F21C1D"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 2,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 10,
			              "md5": "FDBD93F4BE67580E4C805F36607135D4"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 2,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 7,
			              "y": 2,
			              "md5": "7DB01ADDB74B6CCA673F1DA03839DA40"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 2,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 6,
			              "y": 2,
			              "md5": "51DF1D43C64CC4D99AA4AB2660784CB6"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 2,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 5,
			              "y": 2,
			              "md5": "573E8496480036018324EC2B44E0617F"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 2,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 4,
			              "y": 2,
			              "md5": "89CB9E71757C8DAD5A19D3F85F8F4771"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 2,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 1,
			              "md5": "CCC839254D30889091705FDA921D24B6"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          }
			        ],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 8,
			      "y": 2,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 8,
			      "y": 3,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 8,
			      "y": 4,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 8,
			      "y": 5,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 8,
			      "y": 6,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 8,
			      "y": 7,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 8,
			      "y": 8,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 8,
			      "y": 9,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 4,
			        "identifier": "p",
			        "oldColor": 1,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 8,
			      "y": 10,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 4,
			        "identifier": "h",
			        "oldColor": 1,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 8,
			      "y": 11,
			      "md5": "md5"
			    }
			  ],
			  [
			    {
			      "guiPiece": {
			        "color": 3,
			        "identifier": "t",
			        "oldColor": 3,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 9,
			      "y": 0,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 3,
			        "identifier": "p",
			        "oldColor": 3,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 9,
			      "y": 1,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 9,
			      "y": 2,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 9,
			      "y": 3,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 9,
			      "y": 4,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 2,
			        "identifier": "p",
			        "oldColor": 2,
			        "md5": "049F94002F5B1143CC39C3BF14338CB5",
			        "movez": [
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 9,
			              "y": 5,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 5,
			              "md5": "B802C844E146FD29A24C6BA58988B06B"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          }
			        ],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 9,
			      "y": 5,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 9,
			      "y": 6,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 9,
			      "y": 7,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 2,
			        "identifier": "p",
			        "oldColor": 2,
			        "md5": "8B9D406DB3C3ADF301A3756B9F5C89BA",
			        "movez": [
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 9,
			              "y": 8,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 8,
			              "md5": "E465D9C47B99DF88CFCFCEB552D7218C"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          }
			        ],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 9,
			      "y": 8,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 9,
			      "y": 9,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 2,
			        "identifier": "p",
			        "oldColor": 2,
			        "md5": "A0C57FA8D70D0247E2FC4095DFCC42F9",
			        "movez": [
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 9,
			              "y": 10,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 11,
			              "md5": "0C1CB1AEB24046F01E07E73F00EB5CD3"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          }
			        ],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 9,
			      "y": 10,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 4,
			        "identifier": "t",
			        "oldColor": 1,
			        "md5": "md5",
			        "movez": [],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 9,
			      "y": 11,
			      "md5": "md5"
			    }
			  ],
			  [
			    null,
			    null,
			    {
			      "guiPiece": null,
			      "x": 10,
			      "y": 2,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 2,
			        "identifier": "p",
			        "oldColor": 2,
			        "md5": "8DE749A33B21A6814458CD97E49D6025",
			        "movez": [
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 3,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 9,
			              "y": 3,
			              "md5": "E3B5B27108432F40B1F2C85E1FF6AB26"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          }
			        ],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 10,
			      "y": 3,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 2,
			        "identifier": "p",
			        "oldColor": 2,
			        "md5": "B5ABFE1D33F7E6199973F118C0B564CC",
			        "movez": [
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 4,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 9,
			              "y": 4,
			              "md5": "0B3913B65BF2E41039896455516A71A8"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          }
			        ],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 10,
			      "y": 4,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 10,
			      "y": 5,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 2,
			        "identifier": "p",
			        "oldColor": 2,
			        "md5": "539BA7306F1DB7023ED7A6482FE63B69",
			        "movez": [
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 6,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 9,
			              "y": 6,
			              "md5": "B3A60BFFC579899ACBA123D6CDDEC520"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          }
			        ],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 10,
			      "y": 6,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 2,
			        "identifier": "p",
			        "oldColor": 2,
			        "md5": "21623CAA4950C6C1F825E717255DC561",
			        "movez": [
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 7,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 9,
			              "y": 7,
			              "md5": "940446421301ED311A525E571D326A77"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          }
			        ],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 10,
			      "y": 7,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 2,
			        "identifier": "b",
			        "oldColor": 2,
			        "md5": "95A3E4207662F0CEB8A58BB386485F71",
			        "movez": [
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 8,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 7,
			              "md5": "4F276832028890F7DB967FAACED3459F"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 8,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 9,
			              "y": 9,
			              "md5": "BC49F2A3823573BC9B1C54DC770BA7FE"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 8,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 10,
			              "md5": "FDBD93F4BE67580E4C805F36607135D4"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 8,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 9,
			              "y": 7,
			              "md5": "940446421301ED311A525E571D326A77"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 8,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 6,
			              "md5": "BA7359B2D417527B104BD61BBC58D254"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 8,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 7,
			              "y": 5,
			              "md5": "733E3AEBC5C731E02A1B83BCDC6F908A"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 8,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 6,
			              "y": 4,
			              "md5": "40038A87EA134304970FE79A4672B24D"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 8,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 5,
			              "y": 3,
			              "md5": "A473AA3F66DD20BC4E56EEDC9031DC87"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 8,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 4,
			              "y": 2,
			              "md5": "89CB9E71757C8DAD5A19D3F85F8F4771"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          }
			        ],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 10,
			      "y": 8,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 10,
			      "y": 9,
			      "md5": "md5"
			    },
			    null,
			    null
			  ],
			  [
			    null,
			    null,
			    {
			      "guiPiece": null,
			      "x": 11,
			      "y": 2,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 11,
			      "y": 3,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 2,
			        "identifier": "b",
			        "oldColor": 2,
			        "md5": "718738C9E6A7C91D409E7ABB1353E8C5",
			        "movez": [
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 4,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 5,
			              "md5": "FF568A7BFD5E86E759E6B87EE9F365F1"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 4,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 9,
			              "y": 6,
			              "md5": "B3A60BFFC579899ACBA123D6CDDEC520"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 4,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 7,
			              "md5": "B601C84F49FCCECA99007F1B64265163"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 4,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 7,
			              "y": 8,
			              "md5": "A14533DDE0F293A4AC8D2A2119517815"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 4,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 6,
			              "y": 9,
			              "md5": "3DDFABACED5187E884F27FBB925AEB93"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 4,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 5,
			              "y": 10,
			              "md5": "E53A5FC657604537AAC8E956863D0940"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          }
			        ],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 11,
			      "y": 4,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 2,
			        "identifier": "q",
			        "oldColor": 2,
			        "md5": "2151F1B892CC7B24BB1D2872FE56D019",
			        "movez": [
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 5,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 5,
			              "md5": "FF568A7BFD5E86E759E6B87EE9F365F1"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          }
			        ],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 11,
			      "y": 5,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 2,
			        "identifier": "k",
			        "oldColor": 2,
			        "md5": "2C86029BCF5419591FA00C0D65593AC1",
			        "movez": [
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 6,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 7,
			              "md5": "4F276832028890F7DB967FAACED3459F"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 6,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 5,
			              "md5": "FF568A7BFD5E86E759E6B87EE9F365F1"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          }
			        ],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 11,
			      "y": 6,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": null,
			      "x": 11,
			      "y": 7,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 2,
			        "identifier": "h",
			        "oldColor": 2,
			        "md5": "253C367735B01E2C80ECAE2577CAE010",
			        "movez": [
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 8,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 9,
			              "y": 9,
			              "md5": "BC49F2A3823573BC9B1C54DC770BA7FE"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 8,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 9,
			              "y": 7,
			              "md5": "940446421301ED311A525E571D326A77"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          }
			        ],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 11,
			      "y": 8,
			      "md5": "md5"
			    },
			    {
			      "guiPiece": {
			        "color": 2,
			        "identifier": "t",
			        "oldColor": 2,
			        "md5": "C8642F9B8A4C76546243DE1974027E03",
			        "movez": [
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 9,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 10,
			              "y": 9,
			              "md5": "BCACB6F4857F75FD6A4E730C69592F0B"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 9,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 9,
			              "y": 9,
			              "md5": "BC49F2A3823573BC9B1C54DC770BA7FE"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 9,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 8,
			              "y": 9,
			              "md5": "9E069DFB39502A0156D5B4DFD7F21C1D"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          },
			          {
			            "from": {
			              "guiPiece": null,
			              "x": 11,
			              "y": 9,
			              "md5": "md5"
			            },
			            "to": {
			              "guiPiece": null,
			              "x": 7,
			              "y": 9,
			              "md5": "CAECDF77F924F02C6CCAF590685329DD"
			            },
			            "color": null,
			            "identifier": "m",
			            "enPassant": null
			          }
			        ],
			        "pinned": false,
			        "moved": false
			      },
			      "x": 11,
			      "y": 9,
			      "md5": "md5"
			    },
			    null,
			    null
			  ]
			]
						""";

	public void testMate() {

		Alliance alliance = new Alliance(Color.GREEN, Color.RED);

		Board board = new Board(json, alliance, "Red", pollerMock);

		Player player = new Player(2, board);

		player = board.getOwnPlayer();

		player.startCalculation();

		pollerMock.movePiece(EasyMock.capture(piece), EasyMock.capture(place));
		EasyMock.expectLastCall().anyTimes();

		int counter = 10;

		while (counter < 40) {

			for (String piece : piece.getValues()) {

				System.out.print(piece + ", ");
			}

			System.out.println();

			for (String place : place.getValues()) {

				System.out.print(place + ", ");
			}

			System.out.println();

			try {
				Thread.sleep(10_000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("waited " + counter + " seconds...");

			counter += 10;
		}
	}

	@Ignore
	@Test
	public void testMate_0() {

		testMate();
	}
}
