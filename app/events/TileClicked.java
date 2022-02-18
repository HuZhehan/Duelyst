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
	
	boolean TileClicked = false;
	int count = 0;

	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {

		int tilex = message.get("tilex").asInt();
		int tiley = message.get("tiley").asInt();
		
		
		if (gameState.something == true) {
			
			Tile tile = gameState.tile[tilex][tiley];
			TileClicked = true;
			count++;
			
			if (TileClicked) {
				if (count % 2!=0) {
					BasicCommands.addPlayer1Notification(out, "Tile Clicked", 1);
					BasicCommands.drawTile(out, tile, 1);
				}
				else {
					BasicCommands.addPlayer1Notification(out, "Tile unClicked", 1);
					BasicCommands.drawTile(out, tile, 0);
				}
			}
			
			
			
			
			
			
//			if (tile == gameState.tile[tilex][tiley]) {
//				
//				if (tile == gameState.tile[tilex][tiley]) {
//					BasicCommands.drawTile(out, tile, 0);
//				}
//				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
//			}
			
		}
		
	}

}
