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
 * Unit class of Rock_Pulveriser
 * @author Student. Zhehan Hu
 */
public class Rock_Pulveriser extends Unit{

	public Rock_Pulveriser() {
		super();
		attack = 1;
		health = 4;
		maxHealth = 4;
		unitname = "Rock Pulveriser";
		// ownername = "AiPlayer";
		
		// Ability tag: Provoke
		provoke = true;

	}
	public Rock_Pulveriser(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animations, correction);
	}
	public Rock_Pulveriser(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		super(id, animations, correction, currentTile);
	}
	public Rock_Pulveriser(int id, UnitAnimationType animation, Position position, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animation, position, animations, correction);
	}

}
