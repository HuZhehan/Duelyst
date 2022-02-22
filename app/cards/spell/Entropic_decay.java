package cards.spell;

import akka.actor.ActorRef;
import cards.SpellCard;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;
	//@author Student Kanyaphat W. 
public class Entropic_decay extends SpellCard{
	
	public Entropic_decay() {
		super();
		manacost = 5;
		ownername = "AiPlayer";
	}
	
	public Entropic_decay(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
	
	public boolean check(ActorRef out, GameState gameState, Tile tile) {
		if (gameState.player.getMana()<manacost){
			return false;
		}
		if (tile.getUnit()==null){
			return false;
		}
		if (tile.getUnit().getOwner()!=this.getOwner() && tile.getUnit().getId()!= 100 && tile.getUnit().getId()!= 200){
			return true;
		}
		return false;
	}
	
	
	public void content(ActorRef out, GameState gameState, Tile tile) {
		Unit unit = tile.getUnit();
		BasicCommands.playEffectAnimation(out, BasicObjectBuilders.loadEffect(StaticConfFiles.f1_martyrdom), tile);
		BasicCommands.playUnitAnimation(out, unit, UnitAnimationType.hit);
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		BasicCommands.playUnitAnimation(out, unit, UnitAnimationType.death);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		//unit.setHealth(out, gameState, 0);
		//unit.setAttack(out, gameState, 0);
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		BasicCommands.deleteUnit(out, unit);
		
	}
}
