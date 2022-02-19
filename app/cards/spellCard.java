package cards;

import akka.actor.ActorRef;
import structures.GameState;
import structures.basic.BigCard;
import structures.basic.Card;
import structures.basic.MiniCard;
import structures.basic.Tile;

public class SpellCard extends Card{
	
	public SpellCard() {
		super();
		type = "SpellCard";
	}
	
	public SpellCard(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
	public boolean prompt(ActorRef out, GameState gameState, Tile tile) {
		if (gameState.humanPlayer.getMana()<manacost){
			return false;
		}
		if (tile.getUnit()!=null){
			return false;
		}

		return true;
	}
}
