package events;


import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;

/**
 * Indicates that the user has clicked an object on the game canvas, in this case a card.
 * The event returns the position in the player's hand the card resides within.
 * 
 * { 
 *   messageType = “cardClicked”
 *   position = <hand index position [1-6]>
 * }
 * 
 * @author Dr. Richard McCreadie
 * @author Student. Zhehan Hu
 *
 */
public class CardClicked implements EventProcessor{

	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {
		// show info anyhow
		int handPosition = message.get("position").asInt();
		Card card = gameState.player.hand.get(handPosition-1);
		BasicCommands.addPlayer1Notification(out, card.getCardname(), 2);
		
		// should be human's turn
		if (gameState.gameInitalised==true && gameState.player==gameState.humanPlayer && gameState.previousEvent!=PreviousEvent.block) {
			// if the chosen card is not the previous one, clear board and hand
			if((gameState.previousEvent==PreviousEvent.cardClicked&&gameState.previousCard==card)==false) {
				gameState.clear(out);
			}
			// EVENT CASE: click a card, highlight card and valid tiles
			// highlight chosen card
			BasicCommands.drawCard(out, card, handPosition, 1);	
			// highlight all valid tiles
			for (int i=0;i<9;i++) {
				for (int j=0;j<5;j++) {
					Tile t = gameState.tile[i][j];
					if (card.check(out, gameState, t)==true) {
						// empty tile, highlight with white
						if(t.getUnit()==null) {
							BasicCommands.drawTile(out, t, 1);
						}
						// friend or enemy tile, highlight with red
						else if(t.getUnit()!=null) {
							BasicCommands.drawTile(out, t, 2);
						}
						/*// empty tile or friend tile, highlight with white
						if(t.getUnit()==null || t.getUnit().getOwner()==card.getOwner()) {
							BasicCommands.drawTile(out, t, 1);
						}
						// enemy tile, highlight with red
						else if(t.getUnit()!=null && t.getUnit().getOwner()!=card.getOwner()) {
							BasicCommands.drawTile(out, t, 2);
						}*/
						try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
					}	
				}
			}
			// mark this card and this event, for further click event
			gameState.previousEvent = PreviousEvent.cardClicked;
			gameState.previousCard = card;
		}
	}
}
