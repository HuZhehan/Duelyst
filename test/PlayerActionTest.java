import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;

import events.Initalize;
import play.libs.Json;
import structures.GameState;
import structures.basic.Player;

/**
 * 
 * @author Student. Zhehan Hu
 * @author Student. Yuhao Huang
 */

public class PlayerActionTest {
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
