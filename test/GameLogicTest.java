import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;

import commands.BasicCommands;
import commands.CheckMessageIsNotNullOnTell;
import events.EndTurnClicked;
import events.Initalize;
import play.libs.Json;
import structures.GameState;
import structures.basic.Player;
import structures.basic.Tile;
import structures.basic.Unit;
import utils.BasicObjectBuilders;

/**
 * 
 * @author Student. Zhehan Hu
 * @author Student. Yuhao Huang
 */
public class GameLogicTest {
	/**
	 * 
	 */
	@Test
	public void checkTurnEnd() {
		// Initialization
		CheckMessageIsNotNullOnTell altTell = new CheckMessageIsNotNullOnTell(); // create an alternative tell
		BasicCommands.altTell = altTell; // specify that the alternative tell should be used
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// check when player click turn end
		EndTurnClicked turn =  new EndTurnClicked(); // create an EndTurn event processor
		turn.processEvent(null, gameState, eventMessage); // send it to the EndTurn event processor
		assertTrue(gameState.Round == 2); // check that this updated the game state
		assertTrue(gameState.previousEvent == null); // check that this updated the game state
	}
	
	/**
	 * .
	 */
	@Test
	public void checkGameWin() {
		// Initialization
		CheckMessageIsNotNullOnTell altTell = new CheckMessageIsNotNullOnTell(); // create an alternative tell
		BasicCommands.altTell = altTell; // specify that the alternative tell should be used
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		
		// Test
		Player humanPlayer = gameState.humanPlayer;
		Player aiPlayer = gameState.aiPlayer;
		Unit humanAvatar = gameState.humanPlayer.summoned.get(0);
		Unit aiAvatar = gameState.aiPlayer.summoned.get(0);
		// Test before damage
		assertFalse(gameState.gameOver == true);
		// Test after damage
		aiAvatar.takeDamage(null, gameState, 20);
		assertTrue(gameState.gameOver == true);
		
		// another game
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test before damage
		assertFalse(gameState.gameOver == true);
		// Test after damage
		aiPlayer.drawCard(null, gameState, 17);
		aiPlayer.drawCard(null, gameState, 1);
		assertTrue(gameState.gameOver == true);
		
	}
	/**
	 * .
	 */
	@Test
	public void checkGameLose() {
		// Initialization
		CheckMessageIsNotNullOnTell altTell = new CheckMessageIsNotNullOnTell(); // create an alternative tell
		BasicCommands.altTell = altTell; // specify that the alternative tell should be used
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		
		// Test
		Player humanPlayer = gameState.humanPlayer;
		Player aiPlayer = gameState.aiPlayer;
		Unit humanAvatar = gameState.humanPlayer.summoned.get(0);
		Unit aiAvatar = gameState.aiPlayer.summoned.get(0);
		// Test before damage
		assertFalse(gameState.gameOver == true);
		// Test after damage
		humanAvatar.takeDamage(null, gameState, 20);
		assertTrue(gameState.gameOver == true);
		
		// another game
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test before damage
		assertFalse(gameState.gameOver == true);
		// Test after damage
		humanPlayer.drawCard(null, gameState, 17);
		humanPlayer.drawCard(null, gameState, 1);
		assertTrue(gameState.gameOver == true);
	}
	
}
