package events;

import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
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
			gameState.Round++;
			int mana = gameState.Round + 1;
			gameState.humanPlayer.setMana(out, gameState, mana);
			gameState.humanPlayer.drawCard(out, gameState, 1, 0);
			gameState.aiPlayer.setMana(out, gameState, mana);
			gameState.aiPlayer.drawCard(out, gameState, 1, -1);
		}
	}

}
