package cards.spell;

import akka.actor.ActorRef;
import cards.SpellCard;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

/** 
 * SpellCard class of Truestrike. 
 * @author Student. Zhehan Hu
 */
public class Truestrike extends SpellCard{
	
	public Truestrike() {
		super();
		manacost = 1;
		// ownername = "HumanPlayer";
	}
	
	public Truestrike(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
	
	// Check ability: Deal 2 damage to an enemy unit
	public boolean check(ActorRef out, GameState gameState, Tile tile) {
		if (gameState.player.getMana()<manacost){
			return false;
		}
		if (tile.getUnit()==null){
			return false;
		}
		// must be enemy's unit
		if (tile.getUnit().getOwner()!=this.getOwner()){
			return true;
		}
		return false;
	}
	// Apply ability: Deal 2 damage to an enemy unit
	public void content(ActorRef out, GameState gameState, Tile tile) {
		// update states
		Unit unit = tile.getUnit();
		// player animation
		BasicCommands.playEffectAnimation(out, BasicObjectBuilders.loadEffect(StaticConfFiles.f1_inmolation), tile);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		unit.takeDamage(out, gameState, 2);
	}
}
