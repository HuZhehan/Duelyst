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
 *
 */
public class CardClicked implements EventProcessor{

	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {
		
		int handPosition = message.get("position").asInt();
		Card card = gameState.player.hand.get(handPosition-1);
		
		if(gameState.previousEvent==PreviousEvent.cardClicked) {
			gameState.clear(out);
			for (Card c : gameState.player.hand) {
				BasicCommands.drawCard(out, c, gameState.player.hand.indexOf(c)+1, 0);
			}
			try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		}
		BasicCommands.addPlayer1Notification(out, card.getCardname(), 2);
		BasicCommands.drawCard(out, card, handPosition, 1);
		
		for (int i=0;i<9;i++) {
			for (int j=0;j<5;j++) {
				Tile t = gameState.tile[i][j];
				if (card.prompt(out, gameState, t)==true) {
					if(card.getType()=="UnitCard") {
						BasicCommands.drawTile(out, t, 1);
					}
					if(card.getType()=="SpellCard") {
						BasicCommands.drawTile(out, t, 2);
					}
					try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
				}	
			}
		}
		
		
		gameState.previousEvent = PreviousEvent.cardClicked;
		gameState.previousCard = card;

	}

}
