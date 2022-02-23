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
 * @author Student Zhehan Hu
 * @author Student Chinwekele Izuzu
 *
 */
public class TileClicked implements EventProcessor{

	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {
		int tilex = message.get("tilex").asInt();
		int tiley = message.get("tiley").asInt();
		Tile tile = gameState.tile[tilex ][tiley];
		// show info anyhow
		if (tile.getUnit()!=null) {
			BasicCommands.addPlayer1Notification(out, tile.getUnit().getName(), 2);
		}
		// should be human's turn
		if (gameState.gameInitalised==true&&gameState.player==gameState.humanPlayer&&gameState.previousEvent != PreviousEvent.block&&gameState.gameOver==false) {
			// click a card then a valid tile: use card
			// this comes first because we might use a card to heal friend unit, in this case we don't highlight the unit.
			if(gameState.previousEvent==PreviousEvent.cardClicked&&gameState.previousCard.check(out, gameState, tile)==true) {

				int id = gameState.previousCard.getId();
				gameState.clear(out);
				gameState.player.useCard(out, gameState, id, tile);
				
			
			}
			// click a friend unit, highlight valid tile
			else if(tile.getUnit()!=null&&tile.getUnit().getOwner()=="HumanPlayer") {
				Unit unit = tile.getUnit();
				if((gameState.previousEvent==PreviousEvent.unitClicked&&gameState.previousUnit==unit)==false) {
					gameState.clear(out);
				}
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
				// previous event mark
				gameState.previousEvent = PreviousEvent.unitClicked;
				gameState.previousUnit = unit;
			}
			
			// previously click a unit then now click a valid tile: move or attack
			else if(gameState.previousEvent==PreviousEvent.unitClicked&&gameState.previousUnit!=null) {
				Unit previousUnit = gameState.previousUnit;
				if (previousUnit.check(out, gameState, tile) == true){
					// empty tile, move
					if (tile.getUnit()==null) {
						// move
						gameState.clear(out);
						previousUnit.move(out, gameState, tile);
						
					}
					// enemy unit on tile, attack
					else if (tile.getUnit()!=null) {
						// attack
						if (previousUnit.checkAttack(out, gameState, tile) == true){
							gameState.clear(out);
							previousUnit.attack(out, gameState, tile.getUnit());
							
						}
						// move-attack
						else if (previousUnit.checkMoveAttack(out, gameState, tile) == true){
							gameState.clear(out);
							previousUnit.moveAttack(out, gameState, tile.getUnit());
							
						}
						else {
							BasicCommands.addPlayer1Notification(out, "unitAction failure", 2);
						}
					}
				}
			}
		}
	}
}
