import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;

import events.Initalize;
import play.libs.Json;
import structures.GameState;
import structures.basic.Player;
import structures.basic.Unit;

/**
 * This class is about checking the functionality of different card abilities.
 * 
 * @author Student. Zhehan Hu
 * @author Student. Yuhao Huang
 */


public class CardAbilityTest {
	
	/**
	 * This is to check when playing Truestrike, the damage dealt equals the specified value
	 *  on the card.
	 */
	@Test
	public void checkTruestrike() {
		// Initialization
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test
		Player humanPlayer = gameState.humanPlayer;
		Unit aiAvatar = gameState.aiPlayer.summoned.get(0);
		humanPlayer.setMana(9);
		humanPlayer.drawCard(null, gameState, 2);
		// check these before action
		assertFalse(humanPlayer.getMana()==8);
		assertFalse(humanPlayer.hand.size()==4);
		assertFalse(aiAvatar.getHealth()==18);
		// use card of Truestrike
		humanPlayer.useCard(null, gameState, 4, gameState.tile[7][2]);
		// check these after action	
		assertTrue(humanPlayer.getMana()==8); 
		assertTrue(humanPlayer.hand.size()==4);
		assertTrue(aiAvatar.getHealth()==18);
	}
	
	/**
	 * This is to check  if the target has lost health before and if so the card will add health 
	 * value accordingly.
	 */
	@Test
	public void checkSundropElixir() {
		// Initialization
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test
		Player humanPlayer = gameState.humanPlayer;
		Unit humanAvatar = gameState.humanPlayer.summoned.get(0);
		Unit aiAvatar = gameState.aiPlayer.summoned.get(0);
		humanAvatar.takeDamage(null, gameState, 3);
		humanPlayer.setMana(9);
		humanPlayer.drawCard(null, gameState, 5);
		humanPlayer.discard(null, gameState, 0);
		humanPlayer.drawCard(null, gameState, 1);
		// check these before action
		assertFalse(humanPlayer.getMana()==8); 
		assertFalse(humanAvatar.getHealth()==20);
		// use card of SundropElixir
		humanPlayer.useCard(null, gameState, 8, gameState.tile[1][2]);
		// check these after action	
		assertTrue(humanPlayer.getMana()==8); 
		assertTrue(humanAvatar.getHealth()==20);
	}
	/**
	 * This is to check if the unit's attack and health will be added according to the card when
	 * the card was triggered.
	 */
	@Test
	public void checkStaffofYKir() {
		// Initialization
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test
		Player humanPlayer = gameState.humanPlayer;
		Player aiPlayer = gameState.aiPlayer;
		gameState.player = aiPlayer;
		Unit humanAvatar = gameState.humanPlayer.summoned.get(0);
		Unit aiAvatar = gameState.aiPlayer.summoned.get(0);
		aiPlayer.setMana(9);
		// check these before action
		assertFalse(aiPlayer.getMana()==7); 
		assertFalse(aiAvatar.getAttack()==4);
		// use card of SundropElixir
		aiPlayer.useCard(null, gameState, 22, gameState.tile[7][2]);
		// check these after action	
		assertTrue(aiPlayer.getMana()==7); 
		assertTrue(aiAvatar.getAttack()==4);
	}
	
	/**
	 * This is to check if the target die immediately once the destroy effect card was triggered.
	 * 
	 */
	@Test
	public void checkEntropicDecay() {
		// Initialization
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test
		Player humanPlayer = gameState.humanPlayer;
		Player aiPlayer = gameState.aiPlayer;
		Unit humanAvatar = gameState.humanPlayer.summoned.get(0);
		Unit aiAvatar = gameState.aiPlayer.summoned.get(0);
		humanPlayer.useCard(null, gameState, 0, gameState.tile[0][0]);// human player summons combo charger, then ai destory is
		gameState.player = aiPlayer;
		aiPlayer.setMana(9);
		aiPlayer.drawCard(null, gameState, 4);
		aiPlayer.discard(null, gameState, 0);
		aiPlayer.drawCard(null, gameState, 1);
		// check these before action
		assertFalse(aiPlayer.getMana()==4); 
		assertFalse(gameState.tile[0][0].getUnit()==null);
		// use card of EntropicDecay
		aiPlayer.useCard(null, gameState, 27, gameState.tile[0][0]);
		// check these after action	
		assertTrue(aiPlayer.getMana()==4); 
		assertTrue(gameState.tile[0][0].getUnit()==null);
	}
}
