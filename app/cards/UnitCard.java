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
 * super class of spell card
 * @author Zhehan Hu,
 */

public class UnitCard extends Card{
	
	// not used
	protected int attack;
	protected int health;
	
	public UnitCard() {
		super();
		//attack = 0;
		//health = 0;
		type = "UnitCard";
	}
	
	public UnitCard(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
	
	/** 
	 * @method prompt() check if this card can summon unit on this tile, return true if tile is valid
	 * @param tile - tile to check
	 */
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
				// if tile has friend tile in range, its valid to summon unit
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
	/** 
	 * @method content()- summon a unit (whose id = this card's id) on a tile
	 * @param tile - where to summon 
	 */
	public void content(ActorRef out, GameState gameState, Tile tile) {
		gameState.player.summon(out, gameState, id, tile);
	}
}
