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
 * Unit class of Serpenti
 * @author Student. Zhehan Hu
 */
public class Serpenti extends Unit{

	public Serpenti() {
		super();
		attack = 7;
		health = 4;
		maxHealth = 4;
		unitname = "Serpenti";
		// ownername = "AiPlayer";
		
		// Ability tag: Can attack twice per turn
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
