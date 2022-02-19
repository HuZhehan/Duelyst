package cards;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

public class Truestrike extends Card{
	
	public Truestrike() {
		super();
		cardname = "Truestrike";
		type = "Spell";
		manacost = 1;
		ownername = "HumanPlayer";
	}
	
	public Truestrike(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
	
	public void content(ActorRef out, GameState gameState, Tile tile) {
		Unit unit = tile.getUnit();
		int hp = unit.getHealth() - 2;
		BasicCommands.playEffectAnimation(out, BasicObjectBuilders.loadEffect(StaticConfFiles.f1_inmolation), tile);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		unit.setHealth(out, gameState, hp);
	}
}
