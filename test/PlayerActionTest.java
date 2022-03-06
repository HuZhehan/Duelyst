import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;

import events.Initalize;
import play.libs.Json;
import structures.GameState;
import structures.basic.Player;

/**
 * This class is to check the functionality of player's actions such as drawing cards, overdrawing cards,
 * using unit cards, and using spell cards.
 * 
 * @author Student. Zhehan Hu
 * @author Student. Yuhao Huang
 */

public class PlayerActionTest {

	/**
 	* This is to check if the human player could draw the right amount of cards in each turn.
 	*/
	@Test
	public void checkDrawCard() {
		// Initialization
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test
		Player humanPlayer = gameState.humanPlayer;
		assertFalse(humanPlayer.hand.size()==4); // check this before action
		humanPlayer.drawCard(null, gameState, 1);
		assertTrue(humanPlayer.hand.size()==4); // check this after action	
	}

	/**
	* This is to test when the player overdraw (more than 6 cards in hand), the top card will 
	* be deleted.
	**/
	@Test
	public void checkOverDraw() {
		// Initialization
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test
		Player humanPlayer = gameState.humanPlayer;
		assertFalse(humanPlayer.hand.size()==6); // check this before action
		humanPlayer.drawCard(null, gameState, 4); // 3+4>6,discard
		assertTrue(humanPlayer.hand.size()==6); // check this after action	
	}
	
	/**
	 * This is to check if the unit's on-summon effects are triggered and it is activated 
	 * when using a unit card such as combo charger
	*/
	@Test
	public void checkUseUnitCard() {
		// Initialization
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test
		Player humanPlayer = gameState.humanPlayer;
		humanPlayer.setMana(9);
		// check these before action
		assertFalse(humanPlayer.getMana()==8);
		assertFalse(humanPlayer.hand.size()==2);
		assertFalse(gameState.tile[0][0].getUnit()!=null&&gameState.tile[0][0].getUnit().getName()=="Combo Charger");
		// use card of combo charger
		humanPlayer.useCard(null, gameState, 0, gameState.tile[0][0]);
		// check these after action	
		assertTrue(humanPlayer.getMana()==8); 
		assertTrue(humanPlayer.hand.size()==2);
		assertTrue(gameState.tile[0][0].getUnit()!=null&&gameState.tile[0][0].getUnit().getName()=="Combo Charger");
	}
	
	/**
	 * This is to check if there is enough mana in hand when using a spell card such as Truestrike.
	*/
	@Test
	public void checkUseSpellCard() {
		// Initialization
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test
		Player humanPlayer = gameState.humanPlayer;
		humanPlayer.setMana(9);
		humanPlayer.drawCard(null, gameState, 2);
		// check these before action
		assertFalse(humanPlayer.getMana()==8);
		assertFalse(humanPlayer.hand.size()==4);
		// use card of Truestrike
		humanPlayer.useCard(null, gameState, 4, gameState.tile[7][2]);
		// check these after action	
		assertTrue(humanPlayer.getMana()==8); 
		assertTrue(humanPlayer.hand.size()==4);
	}
}
