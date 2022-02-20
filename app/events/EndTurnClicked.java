package events;

import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;

/**
 * Indicates that the user has clicked an object on the game canvas, in this case
 * the end-turn button.
 * 
 * { 
 *   messageType = “endTurnClicked”
 * }
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class EndTurnClicked implements EventProcessor{

	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {
		if (gameState.gameInitalised==true) {
			//player end turn
			gameState.clear(out);
			gameState.humanPlayer.setMana(out, gameState, 0);
			gameState.humanPlayer.drawCard(out, gameState, 1, 0);
			
			// ai start turn
			BasicCommands.addPlayer1Notification(out, "ai Round", 20);
			gameState.aiPlayer.setMana(out, gameState, gameState.Round + 1);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
			
			//ai end turn
			gameState.aiPlayer.setMana(out, gameState, 0);
			gameState.aiPlayer.drawCard(out, gameState, 1, 0);
			
			
			// update
			gameState.Round++;

			//player start new turn
			BasicCommands.addPlayer1Notification(out, "Round"+Integer.toString(gameState.Round), 2);
			gameState.humanPlayer.setMana(out, gameState, gameState.Round+1);

		
			gameState.previousEvent = PreviousEvent.endTurnClicked;
	}

	}
}
