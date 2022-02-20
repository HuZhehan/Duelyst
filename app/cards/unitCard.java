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
		int x = tile.getTilex();
		int y = tile.getTiley();
		for (int i=0;i<9;i++) {
			for (int j=0;j<5;j++) {
				if (Math.abs(x-i)<=1 && Math.abs(y-j)<=1) {
					Tile t = gameState.tile[i][j];
					if(t.getUnit()!=null && t.getUnit().getOwner()==this.getOwner())
					return true;
				}
			}
		}
		return false;
	}
	
	public void act(ActorRef out, GameState gameState, Tile tile) {
		gameState.humanPlayer.summon(out, gameState, id, tile);
	}
}
