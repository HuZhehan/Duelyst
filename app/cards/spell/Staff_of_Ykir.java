package cards.spell;

import akka.actor.ActorRef;
import cards.SpellCard;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

/** 
 * SpellCard class of Staff_of_Ykir. 
 * @author Student. Kanyaphat W.
 */
public class Staff_of_Ykir extends SpellCard{
	
	public Staff_of_Ykir() {
		super();
		manacost = 2;
		// ownername = "AiPlayer";
	}
	
	public Staff_of_Ykir(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}

	// Check ability: Add +2 attack to your avatar.
	public boolean check(ActorRef out, GameState gameState, Tile tile) {
		if (gameState.player.getMana()<manacost){
			return false;
		}
		if (tile.getUnit()==null){
			return false;
		}
		// must be avatar
		if (tile.getUnit().getId()!=100 && tile.getUnit().getId()!=200){
			return false;
		}
		// must be this player's avatar
		if (tile.getUnit().getOwner()==this.getOwner()){
			return true;
		}
		return false;
	}
	// Apply ability: Add +2 attack to your avatar.
	public void content(ActorRef out, GameState gameState, Tile tile) {
		// update states
		Unit unit = tile.getUnit();
		int attack = unit.getAttack() + 2;
		unit.setAttack(attack);
		// play animation
		BasicCommands.playEffectAnimation(out, BasicObjectBuilders.loadEffect(StaticConfFiles.f1_buff), tile);
		BasicCommands.setUnitAttack(out, unit, unit.getAttack());
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
	}
}
