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
 * Unit class of Planar_Scout
 * @author Student. Zhehan Hu
 */
public class Planar_Scout extends Unit{

	public Planar_Scout() {
		super();
		attack = 2;
		health = 1;
		maxHealth = 1;
		unitname = "Planar Scout";
		// ownername = "AiPlayer";

	}
	public Planar_Scout(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animations, correction);
	}
	public Planar_Scout(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		super(id, animations, correction, currentTile);
	}
	public Planar_Scout(int id, UnitAnimationType animation, Position position, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animation, position, animations, correction);
	}
	
}
