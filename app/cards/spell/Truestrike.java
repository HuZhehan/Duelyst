package cards.spell;

import akka.actor.ActorRef;
import cards.SpellCard;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

public class Truestrike extends SpellCard{
	
	public Truestrike() {
		super();
		manacost = 1;
		ownername = "HumanPlayer";
	}
	
	public Truestrike(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
	
	public void content(ActorRef out, GameState gameState, Tile tile) {
		Unit unit = tile.getUnit();
		BasicCommands.playEffectAnimation(out, BasicObjectBuilders.loadEffect(StaticConfFiles.f1_inmolation), tile);
		BasicCommands.playUnitAnimation(out, unit, UnitAnimationType.hit);
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		BasicCommands.playUnitAnimation(out, unit, UnitAnimationType.idle);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		unit.takeDamage(out, gameState, 2);
	}
}
