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

public class AiAvatar extends Unit{
	
	public AiAvatar() {
		super();
		this.maxHealth = 20;
		this.health = 20;
		this.attack = 2;
		this.name = "AiAvatar";
	}
	public AiAvatar(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super();
		this.maxHealth = 20;
		this.health = 20;
		this.attack = 2;
		this.name = "AiAvatar";
	}
	
	public AiAvatar(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		super();
		this.maxHealth = 20;
		this.health = 20;
		this.attack = 2;
		this.name = "AiAvatar";
	}
	
	public AiAvatar(int id, UnitAnimationType animation, Position position, UnitAnimationSet animations,
			ImageCorrection correction) {
		super();
		this.maxHealth = 20;
		this.health = 20;
		this.attack = 2;
		this.name = "AiAvatar";
	}
	
	public void setHealth(ActorRef out, GameState gameState, int health) {
		if (health>20){
			health = 20;
		}
		this.health = health;
		BasicCommands.setUnitHealth(out, this, health);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		if (this.health<0) {
			this.die(out, gameState);
		}
		BasicCommands.addPlayer1Notification(out, "setAiHealth", 2);
		gameState.aiPlayer.setHealth(out, gameState, health);
	}
	
}
