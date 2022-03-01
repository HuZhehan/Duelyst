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
 * Unit class of Ironcliff_Guardian
 * @author Student. Zhehan Hu
 */
public class Ironcliff_Guardian extends Unit{
	
	public Ironcliff_Guardian() {
		super();
		attack = 3;
		health = 10;
		maxHealth = 10;
		unitname = "Ironcliff Guardian";
		// ownername = "HumanPlayer";
		
		// Ability tag: Provoke
		provoke = true;

	}
	public Ironcliff_Guardian(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animations, correction);
	}
	public Ironcliff_Guardian(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		super(id, animations, correction, currentTile);
	}
	public Ironcliff_Guardian(int id, UnitAnimationType animation, Position position, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animation, position, animations, correction);
	}
	
}