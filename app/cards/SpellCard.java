package cards;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

/** 
 * super class of spell card
 * @author Zhehan Hu,
 */

public class SpellCard extends Card{
	
	public SpellCard() {
		super();
		type = "SpellCard";
	}
	
	public SpellCard(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
	/** 
	 * @method prompt() check if this card play affect on this tile, return true if tile is valid
	 * @param tile - tile to check
	 */
	public boolean prompt(ActorRef out, GameState gameState, Tile tile) {
		if (gameState.player.getMana()<manacost){
			return false;
		}
		if (tile.getUnit()==null){
			return false;
		}
		if (tile.getUnit().getOwner()!=this.getOwner()){
			return true;
		}
		return false;
	}
	/** 
	 * @method content()- play spell affect, should code different content() in subClasses
	 * @param tile - where to play affect
	 */
	public void content(ActorRef out, GameState gameState, Tile tile) {
		
	}
}
