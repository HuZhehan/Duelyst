package events;


import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.Tile;
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

// @author Chinwekele Izuzu
public class TileClicked implements EventProcessor{
	
	boolean tileClicked = false;
	
	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {
		int tilex = message.get("tilex").asInt();
		int tiley = message.get("tiley").asInt();
//<<<<<<< HEAD
//		
//		int rightSpace = 8-tilex;
//		int leftSpace = tilex-2;
//		int upSpace = 2 - tiley;
//		int downSpace = 4 - tiley;
//		
//		int max = 0;
//			
//		
//		Tile tile = null;
//		while (tile == gameState.tile[tilex][tiley])
//			tileClicked = true;
//		
//		if (gameState.something == true) {
//			// Checks if a tile clicked has a unit in it. (Condition pending revision for if friendly unit on tile)
//			if (tileClicked && gameState.tile[tilex][tiley].getUnit()!=null);{
//				BasicCommands.addPlayer1Notification(out, gameState.tile[tilex][tiley].getUnit().toString(), 2);
//				
//				// Conditions for right side of the tiles from unit
//				if (rightSpace>=2)max = 2;
//				else if (rightSpace ==0 || rightSpace ==1)max = rightSpace;
//				for (int i =0; i<max; i++)
//					BasicCommands.drawTile(out, gameState.tile[tilex+(i+1)][tiley], 1);
//
//				// Conditions for left side of the tiles from unit
//				if (leftSpace>=0)max = 2;
//				else if(leftSpace == -1)max = 1; 
//				else if(leftSpace == -2)max =0;
//				for (int i =0; i<max; i++)
//					BasicCommands.drawTile(out, gameState.tile[tilex-(i+1)][tiley], 1);
//				
//				// Conditions for down side of the tiles from unit
//				if (downSpace>=2)max =2;
//				else if (downSpace ==0 || downSpace ==1)max = downSpace;
//				for (int i =0; i<max; i++)
//					BasicCommands.drawTile(out, gameState.tile[tilex][tiley+(i+1)], 1);
//
//				// conditions for upside of the tiles from unit
//				if (upSpace<=0)max = 2;
//				else if (upSpace == 1)max = upSpace;else if(upSpace == 2) max = 0;
//				for (int i =0; i<max; i++)
//					BasicCommands.drawTile(out, gameState.tile[tilex][tiley-(i+1)], 1);
//					
//				// conditions for diagonal movement of unit.
//				if ((tilex+1>8 && tiley+1>4) || tiley+1>4 || tilex+1>8) 
//					BasicCommands.drawTile(out, gameState.tile[tilex-1][tiley-1], 1);
//				if ((tiley-1<0 && tilex+1>8) || tiley-1<0 || tilex+1>8) 
//					BasicCommands.drawTile(out, gameState.tile[tilex-1][tiley+1], 1);
//				if ((tiley-1<0 && tilex-1<0) || tiley-1<0 || tilex-1<0)
//					BasicCommands.drawTile(out, gameState.tile[tilex+1][tiley+1], 1);
//				if ((tiley+1>4 && tilex-1<0) || tiley+1>4 || tilex-1<0)
//					BasicCommands.drawTile(out, gameState.tile[tilex+1][tiley-1], 1);
//				else {
//					BasicCommands.drawTile(out, gameState.tile[tilex+1][tiley-1], 1);
//					BasicCommands.drawTile(out, gameState.tile[tilex-1][tiley-1], 1);
//					BasicCommands.drawTile(out, gameState.tile[tilex+1][tiley+1], 1);
//					BasicCommands.drawTile(out, gameState.tile[tilex-1][tiley+1], 1);
//				}			
//		}
//		
//	}
//}
//=======
		Tile tile = gameState.tile[tilex ][tiley];
		// show info anyhow
		if (tile.getUnit()!=null) {
			BasicCommands.addPlayer1Notification(out, tile.getUnit().getName(), 2);
		}
		// should be human's turn
		if (gameState.gameInitalised==true&&gameState.player==gameState.humanPlayer&&gameState.previousEvent != PreviousEvent.block) {
			// click a card then a valid tile: use card
			if(gameState.previousEvent==PreviousEvent.cardClicked) {
				if (gameState.previousCard.check(out, gameState, tile)==true) {
					int id = gameState.previousCard.getId();
					gameState.player.useCard(out, gameState, id, tile);
					gameState.clear(out);
				}
			}
			// click another friend unit, highlight valid tile, mark previous event
			else if(tile.getUnit()!=null&&tile.getUnit().getOwner()=="HumanPlayer") {
				gameState.clear(out);
				Unit unit = tile.getUnit();
				// highlight valid tile
				for (int i=0;i<9;i++) {
					for (int j=0;j<5;j++) {
						Tile t = gameState.tile[i][j];
						if (unit.check(out, gameState, t)==true) {
							// valid tile is empty, highlight with white
							if(t.getUnit()==null) {
								BasicCommands.drawTile(out, t, 1);
							}
							// valid tile has enemy, highlight with red
							else if(t.getUnit()!=null && t.getUnit().getName()!=tile.getUnit().getOwner()) {
								BasicCommands.drawTile(out, t, 2);
							}
							try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
						}	
					}
				}
			
				gameState.previousEvent = PreviousEvent.unitClicked;
				gameState.previousUnit = unit;
			}
			
			// click a unit then a valid tile: move or attack
			else if(gameState.previousEvent==PreviousEvent.unitClicked) {
				if (gameState.previousUnit.check(out, gameState, tile) == true){
					// empty tile, move
					if (tile.getUnit()==null) {
						// move
						gameState.previousUnit.move(out, gameState, tile);
						gameState.clear(out);
					}
					// enemy unit on tile, attack
					else if (tile.getUnit()!=null) {
						// attack
					}

				}

			}

		}
	}
}
