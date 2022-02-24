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
 * This is a representation of a Unit on the game board.
 * A unit has a unique id (this is used by the front-end.
 * Each unit has a current UnitAnimationType, e.g. move,
 * or attack. The position is the physical position on the
 * board. UnitAnimationSet contains the underlying information
 * about the animation frames, while ImageCorrection has
 * information for centering the unit on the tile. 
 * 
 * @author Zhehan Hu
 *
 */

public class Ai_Avatar extends Unit{
	
	public Ai_Avatar() {
		super();
		attack = 2;
		health = 20;
		maxHealth = 20;
		unitname = "Ai Avatar";
		// ownername = "AiPlayer";

	}
	public Ai_Avatar(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animations, correction);
	}
	public Ai_Avatar(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		super(id, animations, correction, currentTile);
	}
	public Ai_Avatar(int id, UnitAnimationType animation, Position position, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animation, position, animations, correction);
	}
	
	// @author Student Zhehan Hu
	public void takeDamage(ActorRef out, GameState gameState, int damage) {
		// update states
		int hp = this.health - damage;
		this.setHealth(hp);
		gameState.aiPlayer.setHealth(hp);
		// play animation
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.hit);
		BasicCommands.setUnitHealth(out, this, health);
		BasicCommands.setPlayer2Health(out, gameState.aiPlayer);;
		try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.idle);
		// check death
		if (this.health<=0) {
			this.die(out, gameState);
			// game end
			gameState.gameEnd(out);
		}
	}
	// @author Student Zhehan Hu
	public void takeHeal(ActorRef out, GameState gameState, int heal) {
		// update states
		this.setHealth(this.health + heal);
		gameState.aiPlayer.setHealth(this.health + heal);
		// play animation
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.channel);
		BasicCommands.setUnitHealth(out, this, health);
		BasicCommands.setPlayer2Health(out, gameState.aiPlayer);;
		try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.idle);
	}
	
}
