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
 * Unit class of Blaze_Hound.
 * @author Student. Zhehan Hu
 */
public class Blaze_Hound extends Unit{

	public Blaze_Hound() {
		super();
		attack = 4;
		health = 3;
		maxHealth = 3;
		unitname = "Blaze Hound";
		// ownername = "AiPlayer";

	}
	public Blaze_Hound(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animations, correction);
	}
	public Blaze_Hound(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		super(id, animations, correction, currentTile);
	}
	public Blaze_Hound(int id, UnitAnimationType animation, Position position, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animation, position, animations, correction);
	}
}
