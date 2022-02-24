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
 * @author Student Zhehan Hu
 *
 */

public class Pyromancer extends Unit{

	public Pyromancer() {
		super();
		attack = 2;
		health = 1;
		maxHealth = 1;
		unitname = "Pyromancer";
		// ownername = "AiPlayer";

	}
	public Pyromancer(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animations, correction);
	}
	public Pyromancer(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		super(id, animations, correction, currentTile);
	}
	public Pyromancer(int id, UnitAnimationType animation, Position position, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animation, position, animations, correction);
	}
	public boolean checkAttack(ActorRef out, GameState gameState, Tile tile) {
		// tile is empty or is friend unit, return false
		if(tile.getUnit()==null||tile.getUnit().getOwner()==this.getOwner()) {
			return false;
		}
		// if target doesn't has provoke but another enemy in range has, return false
		if(tile.getUnit().provoke==false && this.checkProvoked(out, gameState)==true) {
			return false;
		}
		// special situation, two enemy units with provoke
		// one is on adjacent tile, but target is not
		// then we cannot attack target
		// Unit can only attack adjacent unit by default, so super() is called here;
		if(tile.getUnit().provoke==true && this.checkProvoked(out, gameState)==true) {
			if (super.checkAttack(out, gameState, tile)==false) {
				return false;
			}
		}
		return true;
	}
}
