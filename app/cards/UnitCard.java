package cards;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.BigCard;
import structures.basic.Card;
import structures.basic.MiniCard;
import structures.basic.Tile;
import utils.BasicObjectBuilders;

/** 
 * Super class of unit card, 
 * can summon a unit on tile. 
 * @author Student. Zhehan Hu
 */
public class UnitCard extends Card{
	
	public UnitCard() {
		super();
		type = "UnitCard";
	}
	
	public UnitCard(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
	
	public boolean check(ActorRef out, GameState gameState, Tile tile) {
		if (gameState.player.getMana()<manacost){
			return false;
		}
		if (tile.getUnit()!=null) {
			return false;
		}
		// coordinate of tile to check
		int x = tile.getTilex();
		int y = tile.getTiley();
		for (int i=0;i<9;i++) {
			for (int j=0;j<5;j++) {
				// if tile has friend tile in range, it's valid to summon unit
				if (Math.pow((x-i),2)+Math.pow(y-j,2)<=2) {
					Tile friendTile = gameState.tile[i][j];
					if(friendTile.getUnit()!=null && friendTile.getUnit().getOwner()==this.getOwner()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	public void content(ActorRef out, GameState gameState, Tile tile) {
		gameState.player.summon(out, gameState, id, tile);
	}
}
