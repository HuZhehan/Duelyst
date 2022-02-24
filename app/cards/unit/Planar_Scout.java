package cards.unit;

import akka.actor.ActorRef;
import cards.UnitCard;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;
/** 
 * @author Zhehan Hu,
 */
public class Planar_Scout extends UnitCard{
	
	public Planar_Scout() {
		super();
		manacost = 1;
		// attack = 2;
		// health = 1;
		// ownername = "aiPlayer";
	}
	
	public Planar_Scout(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
	// Can be summoned anywhere on the board
	public boolean check(ActorRef out, GameState gameState, Tile tile) {
		if (gameState.player.getMana()<manacost){
			return false;
		}
		if (tile.getUnit()==null) {
			return true;
		}
		return false;
	}
}

