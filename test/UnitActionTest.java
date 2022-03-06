import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;

import commands.BasicCommands;
import commands.CheckMessageIsNotNullOnTell;
import events.Initalize;
import play.libs.Json;
import structures.GameState;
import structures.basic.Player;
import structures.basic.Tile;
import structures.basic.Unit;
import utils.BasicObjectBuilders;

/**
 * This is the class to check a series of Unit Actions such as move, attack, etc.
 * @author Student. Zhehan Hu
 * @author Student. Yuhao Huang
 */

public class UnitActionTest {
	/**
	 * This is to check if the unit can move correctly to the clicked tile.
	*/
	@Test
	public void checkUnitMove() {
		// Initialization
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test
		Unit humanAvatar = gameState.humanPlayer.summoned.get(0);
		assertFalse(gameState.tile[4][2].getUnit()!=null&&gameState.tile[4][2].getUnit().getName()=="Human Avatar"); // check this before action
		humanAvatar.move(null, gameState, gameState.tile[4][2]);
		assertTrue(gameState.tile[4][2].getUnit()!=null&&gameState.tile[4][2].getUnit().getName()=="Human Avatar"); // check this after action
	}
	
	/**
	 * This is to check if the unit's health get decreased when clicking on a unit to attack.
	*/
	@Test
	public void checkUnitAttack() {
		// Initialization
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor	
		// Test
		Unit humanAvatar = gameState.humanPlayer.summoned.get(0);
		Unit aiAvatar = gameState.aiPlayer.summoned.get(0);
		assertFalse(aiAvatar.getHealth()==18); // check this before action
		humanAvatar.move(null, gameState, gameState.tile[6][2]);
		humanAvatar.attack(null, gameState, aiAvatar);
		assertTrue(aiAvatar.getHealth()==18); // check this after action	
	}
	
	/**
	 * This is to check if the on-death effects will be shown when a unit dies.
	*/
	@Test
	public void checkUnitDie() {
		// Initialization
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test
		Unit humanAvatar = gameState.humanPlayer.summoned.get(0);
		assertFalse(gameState.tile[1][2].getUnit()==null); // check this before action
		assertFalse(humanAvatar.getDead()==true); // check this before action
		humanAvatar.die(null, gameState);
		assertTrue(gameState.tile[1][2].getUnit()==null); // check this after action	
		assertTrue(humanAvatar.getDead()==true); // check this after action
	}
	
	/**
	 * This is to check if the attacked unit will counter attack when the human player has a unit attack 
	*/
	@Test
	public void checkUnitCounterAttack() {
		// Initialization
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test
		Unit humanAvatar = gameState.humanPlayer.summoned.get(0);
		Unit aiAvatar = gameState.aiPlayer.summoned.get(0);
		assertFalse(humanAvatar.getHealth()==18); // check this before action
		humanAvatar.move(null, gameState, gameState.tile[6][2]);
		humanAvatar.attack(null, gameState, aiAvatar);
		assertTrue(humanAvatar.getHealth()==18); // check this after action	
	}

}
