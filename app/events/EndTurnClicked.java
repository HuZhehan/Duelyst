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
		// should be human's turn
		if (gameState.gameInitalised==true&&gameState.player==gameState.humanPlayer) {
			//player end turn
			gameState.clear(out);
			gameState.humanPlayer.setMana(0);
			BasicCommands.setPlayer1Mana(out, gameState.humanPlayer);			
			gameState.humanPlayer.drawCard(out, gameState, 1, 0);
			
			// ai start turn
			BasicCommands.addPlayer1Notification(out, "ai Round", 20);
			gameState.player = gameState.aiPlayer;
			gameState.aiPlayer.setMana(gameState.Round + 1);
			BasicCommands.setPlayer2Mana(out, gameState.aiPlayer);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
			
			//ai end turn
			gameState.aiPlayer.setMana(0);
			BasicCommands.setPlayer2Mana(out, gameState.aiPlayer);
			gameState.aiPlayer.drawCard(out, gameState, 1, 0);
			
			
			// update
			gameState.Round++;

			//player start new turn
			BasicCommands.addPlayer1Notification(out, "Round"+Integer.toString(gameState.Round), 2);
			gameState.player = gameState.humanPlayer;
			gameState.humanPlayer.setMana(gameState.Round+1);
			BasicCommands.setPlayer1Mana(out, gameState.humanPlayer);

		
			gameState.previousEvent = PreviousEvent.endTurnClicked;
	}

	}
}
