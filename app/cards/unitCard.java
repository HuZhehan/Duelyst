package cards;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.BigCard;
import structures.basic.Card;
import structures.basic.MiniCard;
import structures.basic.Tile;
import utils.BasicObjectBuilders;

public class UnitCard extends Card{
	
	public UnitCard() {
		super();
		type = "UnitCard";
	}
	
	public UnitCard(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
	
	public boolean prompt(ActorRef out, GameState gameState, Tile tile) {
		if (gameState.player.getMana()<manacost){
			return false;
		}
		if (tile.getUnit()!=null) {
			return false;
		}
		return false;
	}
	
	public void content(ActorRef out, GameState gameState, Tile tile) {
		gameState.humanPlayer.summon(out, gameState, id, tile);
	}
}
