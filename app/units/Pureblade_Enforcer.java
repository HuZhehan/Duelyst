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
 * Unit class of Pureblade_Enforcer
 * @author Student. Zhehan Hu
 */
public class Pureblade_Enforcer extends Unit{
	
	public Pureblade_Enforcer() {
		super();
		attack = 1;
		health = 4;
		maxHealth = 4;
		unitname = "Pureblade Enforcer";
		// ownername = "HumanPlayer";
		
		// skill tags
		spellThief = true;

	}
	public Pureblade_Enforcer(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animations, correction);
	}
	public Pureblade_Enforcer(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		super(id, animations, correction, currentTile);
	}
	public Pureblade_Enforcer(int id, UnitAnimationType animation, Position position, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animation, position, animations, correction);
	}

	// Ability: If the enemy player casts a spell,
	public boolean checkSkill(ActorRef out, GameState gameState, Player player) {
		if (this.getOwner()!=player.getName()){
			return true;
		}
		return false;
	}
	//  this minion gains +1 attack and +1 health
	public void useSkill(ActorRef out, GameState gameState) {
		//BasicCommands.addPlayer1Notification(out, "Trigger: SpellThief", 2);
		int a = this.getAttack() + 1;
		this.setAttack(a);
		
		// 
		int x = this.getPosition().getTilex();
		int y = this.getPosition().getTiley();
		Tile tile = gameState.tile[x][y];
		
		// play animation
		BasicCommands.playEffectAnimation(out, BasicObjectBuilders.loadEffect(StaticConfFiles.f1_buff), tile);
		BasicCommands.setUnitAttack(out, this, this.getAttack());
		this.takeHeal(out, gameState, 1);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
	}

}