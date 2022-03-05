package cards;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

/** 
 * Super class of spell card
 * can apply affect on tile
 * @author Student. Zhehan Hu
 */
public class SpellCard extends Card{
	
	public SpellCard() {
		super();
		type = "SpellCard";
	}
	
	public SpellCard(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}

	public boolean check(ActorRef out, GameState gameState, Tile tile) {
		return false;
	}
	public void content(ActorRef out, GameState gameState, Tile tile) {
		
	}
}
