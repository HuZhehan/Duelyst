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
 * Unit class of Azure_Herald
 * @author Student. Zhehan Hu
 */
public class Azure_Herald extends Unit{
	
	public Azure_Herald() {
		super();
		attack = 1;
		health = 4;
		maxHealth = 4;
		unitname = "Azure Herald";
		// ownername = "HumanPlayer";

	}
	public Azure_Herald(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animations, correction);
	}
	public Azure_Herald(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		super(id, animations, correction, currentTile);
	}
	public Azure_Herald(int id, UnitAnimationType animation, Position position, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animation, position, animations, correction);
	}
	
}