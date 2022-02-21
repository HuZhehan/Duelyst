package cards.spell;

import akka.actor.ActorRef;
import cards.SpellCard;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;
	//@author Student Kanyaphat W. 
public class Staff_of_Ykir extends SpellCard{
	
	public Staff_of_Ykir() {
		super();
		manacost = 2;
		ownername = "AiPlayer";
	}
	
	public Staff_of_Ykir(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
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
		Unit unit = tile.getUnit();
		int hp = unit.getAttack() + 2;
		BasicCommands.playEffectAnimation(out, BasicObjectBuilders.loadEffect(StaticConfFiles.f1_buff), tile);
		BasicCommands.playUnitAnimation(out, unit, UnitAnimationType.hit);
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		BasicCommands.playUnitAnimation(out, unit, UnitAnimationType.idle);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		//unit.setAttack(out, gameState, hp);
	}
}
