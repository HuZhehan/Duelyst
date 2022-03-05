package cards.spell;

import akka.actor.ActorRef;
import cards.SpellCard;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

/** 
 * SpellCard class of Sundrop_Elixir. 
 * @author Student. Kanyaphat W.
 */
public class Sundrop_Elixir extends SpellCard{
	
	public Sundrop_Elixir() {
		super();
		manacost = 1;
		// ownername = "HumanPlayer";
	}
	
	public Sundrop_Elixir(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}

	// Check ability: Add +5 health to a Unit. This cannot take a unit over its starting health value.
	public boolean check(ActorRef out, GameState gameState, Tile tile) {
		if (gameState.player.getMana()<manacost){
			return false;
		}
		if (tile.getUnit()==null){
			return false;
		}
		// must be this player's unit
		if (tile.getUnit().getOwner()==this.getOwner()){
			return true;
		}
		return false;
	}
	// Apply ability: Add +5 health to a Unit. This cannot take a unit over its starting health value.
	public void content(ActorRef out, GameState gameState, Tile tile) {
		// update states
		Unit unit = tile.getUnit();
		// play animation
		BasicCommands.playEffectAnimation(out, BasicObjectBuilders.loadEffect(StaticConfFiles.f1_buff), tile);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		unit.takeHeal(out, gameState, 5);
	}
}
