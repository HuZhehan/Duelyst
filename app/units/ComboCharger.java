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
 * Unit class of ComboCharger
 * @author Student. Zhehan Hu
 */
public class ComboCharger extends Unit{
	
	public ComboCharger() {
		super();
		attack = 1;
		health = 3;
		maxHealth = 3;
		unitname = "Combo Charger";
		// ownername = "HumanPlayer";

	}
	public ComboCharger(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animations, correction);
	}
	public ComboCharger(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		super(id, animations, correction, currentTile);
	}
	public ComboCharger(int id, UnitAnimationType animation, Position position, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animation, position, animations, correction);
	}
	
}