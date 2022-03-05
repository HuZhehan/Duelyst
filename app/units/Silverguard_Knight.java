package units;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.ImageCorrection;
import structures.basic.Player;
import structures.basic.Position;
import structures.basic.Tile;
import structures.basic.Unit;
import structures.basic.UnitAnimationSet;
import structures.basic.UnitAnimationType;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

/** 
 * Unit class of Silverguard_Knight
 * @author Student. Zhehan Hu
 */
public class Silverguard_Knight extends Unit{
	
	public Silverguard_Knight() {
		super();
		attack = 1;
		health = 5;
		maxHealth = 5;
		unitname = "Silverguard Knight";
		// ownername = "HumanPlayer";
		
		// Ability tags
		provoke = true;
		avatarDamaged = true;

	}
	public Silverguard_Knight(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animations, correction);
	}
	public Silverguard_Knight(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		super(id, animations, correction, currentTile);
	}
	public Silverguard_Knight(int id, UnitAnimationType animation, Position position, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animation, position, animations, correction);
	}
	
	public boolean checkSkill(ActorRef out, GameState gameState, Unit unit) {
		if (this.getOwner()==unit.getOwner()){
			return true;
		}
		return false;
	}
	// Ability: If your avatar is dealt damage this unit gains +2 attack
	public void useSkill(ActorRef out, GameState gameState) {
		//BasicCommands.addPlayer1Notification(out, "Trigger: AvatarDamaged", 2);
		int a = this.getAttack() + 2;
		this.setAttack(a);
		// 
		int x = this.getPosition().getTilex();
		int y = this.getPosition().getTiley();
		Tile tile = gameState.tile[x][y];
		
		// play animation
		BasicCommands.playEffectAnimation(out, BasicObjectBuilders.loadEffect(StaticConfFiles.f1_buff), tile);
		BasicCommands.setUnitAttack(out, this, this.getAttack());
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
	}
}