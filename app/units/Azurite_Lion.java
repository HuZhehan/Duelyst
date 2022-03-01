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
 * Unit class of Azurite_Lion.
 * @author Student. Zhehan Hu
 */
public class Azurite_Lion extends Unit{
	
	public Azurite_Lion() {
		super();
		attack = 2;
		health = 3;
		maxHealth = 3;
		unitname = "Azurite Lion";
		// ownername = "HumanPlayer";
		
		// Can attack twice per turn.
		maxAttackChance = 2; 
	}
	public Azurite_Lion(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animations, correction);
	}
	public Azurite_Lion(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		super(id, animations, correction, currentTile);
	}
	public Azurite_Lion(int id, UnitAnimationType animation, Position position, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animation, position, animations, correction);
	}
	
}