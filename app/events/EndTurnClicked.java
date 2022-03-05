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
 * @author Student. Zhehan Hu
 */
public class EndTurnClicked implements EventProcessor{

	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {
		// should be human's turn
		if (gameState.gameInitalised==true && gameState.player==gameState.humanPlayer && gameState.previousEvent!=PreviousEvent.block) {
			// block player's click
			gameState.previousEvent = PreviousEvent.block;
			// game may be end to break the process
			// check gameOver after every turn phase
			while(gameState.gameOver == false) {
				//player end turn
				gameState.humanTurnEnd(out);
				if (gameState.gameOver == true){break;}

				// ai's turn start
				gameState.aiTurnStart(out);
				if (gameState.gameOver == true){break;}
				
				// ai uses cards
				gameState.aiTurnUse(out);
				if (gameState.gameOver == true){break;}

				//ai's turn end
				gameState.aiTurnEnd(out);
				if (gameState.gameOver == true){break;}

				//player start new turn
				gameState.Round++;
				gameState.humanTurnStart(out);
				if (gameState.gameOver == true){break;}
				break;
			}
			// active player's click
			gameState.previousEvent = null;
		}

	}
}
