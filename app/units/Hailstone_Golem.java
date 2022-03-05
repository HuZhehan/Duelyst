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
 * Unit class of Hailstone_Golem
 * @author Student. Zhehan Hu
 */
public class Hailstone_Golem extends Unit{
	
	public Hailstone_Golem() {
		super();
		attack = 4;
		health = 6;
		maxHealth = 6;
		unitname = "Hailstone Golem";
		// ownername = "HumanPlayer";
		// ownername = "AiPlayer";

	}
	public Hailstone_Golem(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animations, correction);
	}
	public Hailstone_Golem(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		super(id, animations, correction, currentTile);
	}
	public Hailstone_Golem(int id, UnitAnimationType animation, Position position, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animation, position, animations, correction);
	}
	
}