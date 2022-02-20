package events;


import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;

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
public class TileClicked implements EventProcessor{

	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {
		int tilex = message.get("tilex").asInt();
		int tiley = message.get("tiley").asInt();
		Tile tile = gameState.tile[tilex ][tiley];
		// do some logic
		if (tile.getUnit()!=null) {
			BasicCommands.addPlayer1Notification(out, gameState.tile[tilex][tiley].getUnit().getName(), 2);
		}
		
		// click a card then a valid tile: use card
		if(gameState.previousEvent==PreviousEvent.cardClicked) {
			if (gameState.previousCard.prompt(out, gameState, tile)==true) {
				int id = gameState.previousCard.getId();
				gameState.player.useCard(out, gameState, id, tile);
				gameState.clear(out);
			}
		}
		
		// click a unit then a valid tile: move or attack
		if(gameState.previousEvent==PreviousEvent.unitClicked) {
			
		}
	}
}
