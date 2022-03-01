package units;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.ImageCorrection;
import structures.basic.Position;
import structures.basic.Tile;
import structures.basic.Unit;
import structures.basic.UnitAnimationSet;
import structures.basic.UnitAnimationType;

/** 
 * Unit class of Windshrike
 * @author Student. Chinwekele Izuzu
 */
public class Windshrike extends Unit{

	public Windshrike() {
		super();
		attack = 4;
		health = 3;
		maxHealth = 3;
		unitname = "Windshrike";
		// ownername = "AiPlayer";

	}
	public Windshrike(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animations, correction);
	}
	public Windshrike(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		super(id, animations, correction, currentTile);
	}
	public Windshrike(int id, UnitAnimationType animation, Position position, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animation, position, animations, correction);
	}
	
	// Ability: can move to anywhere on the board.
	public boolean checkMove(ActorRef out, GameState gameState, Tile tile) {
		if (tile.getUnit() == null) {
			return true;
		}
		return false;
	}
	// Ability: When this unit dies, its owner draws a card
	public void die(ActorRef out, GameState gameState) {
		// When this unit dies, its owner draws a card
		//BasicCommands.addPlayer1Notification(out, "Trigger: Death", 2);
		if (this.getOwner()=="HumanPlayer"){
				gameState.humanPlayer.drawCard(out, gameState, 1);
		}
		else if (this.getOwner()=="HumanPlayer") {
			gameState.aiPlayer.drawCard(out, gameState, 1);
		}
		// play animation
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.death);
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		// destroy Unit & reset tile
		this.dead = true;
		BasicCommands.deleteUnit(out, this);
		int x = getPosition().getTilex();
		int y = getPosition().getTiley();
		gameState.tile[x][y].setUnit(null);
	}
}
