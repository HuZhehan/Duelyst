import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;

import commands.BasicCommands;
import commands.CheckMessageIsNotNullOnTell;
import events.Initalize;
import play.libs.Json;
import structures.GameState;
import structures.basic.Card;
import structures.basic.Player;
import structures.basic.Unit;

/**
 * 
 * @author Student. Zhehan Hu
 * @author Student. Yuhao Huang
 */

public class UnitAbilityTest {
	
	@Test
	public void checkHeal() {
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
		humanAvatar.takeDamage(null, gameState, 2); // human avatar health = 18
		humanPlayer.setMana(9);
		humanPlayer.drawCard(null, gameState, 3);
		// check these before action
		assertFalse(humanAvatar.getHealth()==20);
		// use card of Azure Herald
		humanPlayer.useCard(null, gameState, 5, gameState.tile[0][0]);
		// check these after action	
		assertTrue(humanAvatar.getHealth()==20);
	}
	
	
	@Test
	public void checkProvoke() {
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
		// move human avatar in suitable tile
		humanAvatar.move(null, gameState, gameState.tile[6][2]);
		humanAvatar.enableMoveAttack();
		// check these before action
		assertTrue(humanAvatar.checkAttack(null, gameState, gameState.tile[7][2]));// can attack ai avatar
		assertFalse(humanAvatar.checkAttack(null, gameState, gameState.tile[6][1]));// cannot attack empty tile
		// ai summon Rock Pulveriser
		aiPlayer.summon(null, gameState, 20, gameState.tile[6][1]);
		// check these after action	
		assertFalse(humanAvatar.checkAttack(null, gameState, gameState.tile[7][2]));// cannot attack ai avatar
		assertTrue(humanAvatar.checkAttack(null, gameState, gameState.tile[6][1]));// can attack Rock Pulveriser 
	}
	
	@Test
	public void checkAttackTwice() {
		// Initialization
		CheckMessageIsNotNullOnTell altTell = new CheckMessageIsNotNullOnTell(); // create an alternative tell
		BasicCommands.altTell = altTell; // specify that the alternative tell should be used
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test Serpenti
		Player humanPlayer = gameState.humanPlayer;
		Player aiPlayer = gameState.aiPlayer;
		aiPlayer.summon(null, gameState, 26, gameState.tile[2][2]);// summon Serpenti near human avatar
		Unit humanAvatar = gameState.humanPlayer.summoned.get(0);
		Unit serpenti = gameState.aiPlayer.summoned.get(1);
		serpenti.enableMoveAttack();
		// check these before first attack
		assertTrue(serpenti.check(null, gameState, gameState.tile[1][2]));// can attack human avatar
		// first attack
		serpenti.attack(null, gameState, humanAvatar);
		// second check
		assertTrue(serpenti.check(null, gameState, gameState.tile[1][2]));// can attack human avatar
		// second attack
		serpenti.attack(null, gameState, humanAvatar);
		// third check
		assertFalse(serpenti.check(null, gameState, gameState.tile[1][2]));// cannot attack human avatar	
	}
	
	@Test
	public void checkAirdrop() {
		// Initialization
		CheckMessageIsNotNullOnTell altTell = new CheckMessageIsNotNullOnTell(); // create an alternative tell
		BasicCommands.altTell = altTell; // specify that the alternative tell should be used
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test Planar Scout
		Player humanPlayer = gameState.humanPlayer;
		Player aiPlayer = gameState.aiPlayer;
		gameState.player = aiPlayer;
		aiPlayer.setMana(9);
		// draw card of planarScout
		aiPlayer.drawCard(null, gameState, 5);
		aiPlayer.discard(null, gameState, 0);
		aiPlayer.drawCard(null, gameState, 1);
		Card planarScout = gameState.aiPlayer.hand.get(5);
		// check four corners
		assertTrue(planarScout.check(null, gameState, gameState.tile[0][0]));// check left top
		assertTrue(planarScout.check(null, gameState, gameState.tile[0][4]));// check left bottom
		assertTrue(planarScout.check(null, gameState, gameState.tile[8][4]));// check right bottom
		assertTrue(planarScout.check(null, gameState, gameState.tile[8][0]));// check right top	
	}
	
	@Test
	public void checkFlying() {
		// Initialization
		CheckMessageIsNotNullOnTell altTell = new CheckMessageIsNotNullOnTell(); // create an alternative tell
		BasicCommands.altTell = altTell; // specify that the alternative tell should be used
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test Windshrike
		Player humanPlayer = gameState.humanPlayer;
		Player aiPlayer = gameState.aiPlayer;
		aiPlayer.summon(null, gameState, 24, gameState.tile[4][2]);// summon Windshrike in center
		Unit humanAvatar = gameState.humanPlayer.summoned.get(0);
		Unit windshrike = gameState.aiPlayer.summoned.get(1);
		windshrike.enableMoveAttack();
		// check four corners
		assertTrue(windshrike.checkMove(null, gameState, gameState.tile[0][0]));// check left top
		assertTrue(windshrike.checkMove(null, gameState, gameState.tile[0][4]));// check left bottom
		assertTrue(windshrike.checkMove(null, gameState, gameState.tile[8][4]));// check right bottom
		assertTrue(windshrike.checkMove(null, gameState, gameState.tile[8][0]));// check right top	
	}
	
	@Test
	public void checkRanged() {
		// Initialization
		CheckMessageIsNotNullOnTell altTell = new CheckMessageIsNotNullOnTell(); // create an alternative tell
		BasicCommands.altTell = altTell; // specify that the alternative tell should be used
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test fireSpitter
		Player humanPlayer = gameState.humanPlayer;
		Player aiPlayer = gameState.aiPlayer;
		humanPlayer.summon(null, gameState, 2, gameState.tile[0][0]); // summon fireSpitter in corner
		Unit fireSpitter = gameState.humanPlayer.summoned.get(1);
		fireSpitter.enableMoveAttack();
		// check these before action
		assertFalse(fireSpitter.checkAttack(null, gameState, gameState.tile[5][1]));// cannot attack empty tile
		assertFalse(fireSpitter.checkAttack(null, gameState, gameState.tile[6][1]));// cannot attack empty tile
		// ai summon Rock Pulveriser
		aiPlayer.summon(null, gameState, 20, gameState.tile[6][1]);
		aiPlayer.summon(null, gameState, 30, gameState.tile[5][1]);
		// check these after action	
		assertTrue(fireSpitter.checkAttack(null, gameState, gameState.tile[5][1]));// can attack Rock Pulveriser out of range
		assertTrue(fireSpitter.checkAttack(null, gameState, gameState.tile[6][1]));// can attack Rock Pulveriser out of range
	}
	
	@Test
	public void checkRangedProvoke() {
		// Initialization
		CheckMessageIsNotNullOnTell altTell = new CheckMessageIsNotNullOnTell(); // create an alternative tell
		BasicCommands.altTell = altTell; // specify that the alternative tell should be used
		GameState gameState = new GameState(); // create state storage
		Initalize initalize =  new Initalize(); // create an initalize event processor
		ObjectNode eventMessage = Json.newObject(); // create a dummy message
		initalize.processEvent(null, gameState, eventMessage); // send it to the initalize event processor
		// Test fireSpitter
		Player humanPlayer = gameState.humanPlayer;
		Player aiPlayer = gameState.aiPlayer;
		humanPlayer.summon(null, gameState, 2, gameState.tile[4][2]);
		Unit fireSpitter = gameState.humanPlayer.summoned.get(1);
		fireSpitter.enableMoveAttack();
		// check these before action
		assertFalse(fireSpitter.checkAttack(null, gameState, gameState.tile[5][1]));// cannot attack empty tile
		assertFalse(fireSpitter.checkAttack(null, gameState, gameState.tile[6][1]));// cannot attack empty tile
		// ai summon Rock Pulveriser
		aiPlayer.summon(null, gameState, 20, gameState.tile[6][1]);
		aiPlayer.summon(null, gameState, 30, gameState.tile[5][1]);
		// check these after action	
		assertTrue(fireSpitter.checkAttack(null, gameState, gameState.tile[5][1]));// can attack Rock Pulveriser
		assertFalse(fireSpitter.checkAttack(null, gameState, gameState.tile[6][1]));// cannot attack Rock Pulveriser out of range
	}
}
