package events;


import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.Tile;

/**
 * Indicates that the user has clicked an object on the game canvas, in this case a tile.
 * The event returns the x (horizontal) and y (vertical) indices of the tile that was
 * clicked. Tile indices start at 1.
 * 
 * { 
 *   messageType = “tileClicked”
 *   tilex = <x index of the tile>
 *   tiley = <y index of the tile>
 * }
 * 
 * @author Dr. Richard McCreadie
 *
 */

// @author Chinwekele Izuzu
public class TileClicked implements EventProcessor{
	
	boolean tileClicked = false;
	
	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {

		int tilex = message.get("tilex").asInt();
		int tiley = message.get("tiley").asInt();
		
		int rightSpace = 8-tilex;
		int leftSpace = tilex-2;
		int upSpace = 2 - tiley;
		int downSpace = 4 - tiley;
		
		int max = 0;
			
		
		Tile tile = null;
		while (tile == gameState.tile[tilex][tiley])
			tileClicked = true;
		
		if (gameState.something == true) {
			// Checks if a tile clicked has a unit in it. (Condition pending revision for if friendly unit on tile)
			if (tileClicked && gameState.tile[tilex][tiley].getUnit()!=null);{
				BasicCommands.addPlayer1Notification(out, gameState.tile[tilex][tiley].getUnit().toString(), 2);
				
				// Conditions for right side of the tiles from unit
				if (rightSpace>=2)max = 2;
				else if (rightSpace ==0 || rightSpace ==1)max = rightSpace;
				for (int i =0; i<max; i++)
					BasicCommands.drawTile(out, gameState.tile[tilex+(i+1)][tiley], 1);

				// Conditions for left side of the tiles from unit
				if (leftSpace>=0)max = 2;
				else if(leftSpace == -1)max = 1; 
				else if(leftSpace == -2)max =0;
				for (int i =0; i<max; i++)
					BasicCommands.drawTile(out, gameState.tile[tilex-(i+1)][tiley], 1);
				
				// Conditions for down side of the tiles from unit
				if (downSpace>=2)max =2;
				else if (downSpace ==0 || downSpace ==1)max = downSpace;
				for (int i =0; i<max; i++)
					BasicCommands.drawTile(out, gameState.tile[tilex][tiley+(i+1)], 1);

				// conditions for upside of the tiles from unit
				if (upSpace<=0)max = 2;
				else if (upSpace == 1)max = upSpace;else if(upSpace == 2) max = 0;
				for (int i =0; i<max; i++)
					BasicCommands.drawTile(out, gameState.tile[tilex][tiley-(i+1)], 1);
					
				// conditions for diagonal movement of unit.
				if ((tilex+1>8 && tiley+1>4) || tiley+1>4 || tilex+1>8) 
					BasicCommands.drawTile(out, gameState.tile[tilex-1][tiley-1], 1);
				if ((tiley-1<0 && tilex+1>8) || tiley-1<0 || tilex+1>8) 
					BasicCommands.drawTile(out, gameState.tile[tilex-1][tiley+1], 1);
				if ((tiley-1<0 && tilex-1<0) || tiley-1<0 || tilex-1<0)
					BasicCommands.drawTile(out, gameState.tile[tilex+1][tiley+1], 1);
				if ((tiley+1>4 && tilex-1<0) || tiley+1>4 || tilex-1<0)
					BasicCommands.drawTile(out, gameState.tile[tilex+1][tiley-1], 1);
				else {
					BasicCommands.drawTile(out, gameState.tile[tilex+1][tiley-1], 1);
					BasicCommands.drawTile(out, gameState.tile[tilex-1][tiley-1], 1);
					BasicCommands.drawTile(out, gameState.tile[tilex+1][tiley+1], 1);
					BasicCommands.drawTile(out, gameState.tile[tilex-1][tiley+1], 1);
				}			
		}
		
	}
}
}
