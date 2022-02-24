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
 * @author Chinwekele Izuzu
 *
 */

public class Serpenti extends Unit{

	public Serpenti() {
		super();
		attack = 7;
		health = 4;
		maxHealth = 4;
		unitname = "Serpenti";
		// ownername = "AiPlayer";
		
		// Can attack twice per turn
		maxAttackChance = 2; 

	}
	public Serpenti(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animations, correction);
	}
	public Serpenti(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		super(id, animations, correction, currentTile);
	}
	public Serpenti(int id, UnitAnimationType animation, Position position, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animation, position, animations, correction);
	}
}
