package cards.spell;

import akka.actor.ActorRef;
import cards.SpellCard;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

/** 
 * SpellCard class of Entropic_Decay. 
 * @author Student. Kanyaphat W.
 */
public class Entropic_Decay extends SpellCard{
	
	public Entropic_Decay() {
		super();
		manacost = 5;
		// ownername = "AiPlayer";
	}
	
	public Entropic_Decay(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}

	// Check ability: Reduce a non-avatar unit to 0 health.
	public boolean check(ActorRef out, GameState gameState, Tile tile) {
		if (gameState.player.getMana()<manacost){
			return false;
		}
		if (tile.getUnit()==null){
			return false;
		}
		// must be none-avatar
		if (tile.getUnit().getId()== 100 || tile.getUnit().getId()==200) {
			return false;
		}
		// must be enemy's unit
		if (tile.getUnit().getOwner()!=this.getOwner()) {
			return true;
		}
		return false;
	}
	// Apply ability: Reduce a non-avatar unit to 0 health.
	public void content(ActorRef out, GameState gameState, Tile tile) {
		// update states
		Unit unit = tile.getUnit();
		int damage = unit.getHealth();
		// player animation
		BasicCommands.playEffectAnimation(out, BasicObjectBuilders.loadEffect(StaticConfFiles.f1_martyrdom), tile);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		unit.takeDamage(out, gameState, damage);
	}
}
