package cards.spell;

import akka.actor.ActorRef;
import cards.SpellCard;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;
// @author Student Kanyaphat W. 
// @author Student Zhehan Hu
public class Sundrop_Elixir extends SpellCard{
	
	public Sundrop_Elixir() {
		super();
		manacost = 1;
		// ownername = "HumanPlayer";
	}
	
	public Sundrop_Elixir(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
	
	public boolean check(ActorRef out, GameState gameState, Tile tile) {
		if (gameState.player.getMana()<manacost){
			return false;
		}
		if (tile.getUnit()==null){
			return false;
		}
		if (tile.getUnit().getOwner()==this.getOwner()){
			return true;
		}
		return false;
	}
	public void content(ActorRef out, GameState gameState, Tile tile) {
		// update states
		Unit unit = tile.getUnit();
		// play animation
		BasicCommands.playEffectAnimation(out, BasicObjectBuilders.loadEffect(StaticConfFiles.f1_buff), tile);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		unit.takeHeal(out, gameState, 5);
	}
}
