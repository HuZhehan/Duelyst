package cards;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

public class SpellCard extends Card{
	
	public SpellCard() {
		super();
		type = "SpellCard";
	}
	
	public SpellCard(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
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
	public void act(ActorRef out, GameState gameState, Tile tile) {
	}
}
