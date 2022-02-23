package events;

import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.Card;
import structures.basic.Unit;

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
		if (gameState.gameInitalised==true&&gameState.player==gameState.humanPlayer&&gameState.previousEvent != PreviousEvent.block&&gameState.gameOver==false) {
			// block player's click
			gameState.previousEvent = PreviousEvent.block;
			
			//player end turn
			gameState.humanTurnEnd(out);

			// ai's turn start
			gameState.humanTurnStart(out);

			//ai's turn end
			gameState.aiTurnEnd(out);

			//player start new turn
			gameState.Round++;
			gameState.humanTurnStart(out);
			
			// active player's click
			gameState.previousEvent = null;
		}

	}
}
