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
public class Ironcliff_Guardian extends UnitCard{
	
	public Ironcliff_Guardian() {
		super();
		manacost = 5;
		attack = 3;
		health = 10;
		ownername = "HumanPlayer";
	}
	
	public Ironcliff_Guardian(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
	public boolean check(ActorRef out, GameState gameState, Tile tile) {
		if (gameState.player.getMana()<manacost){
			return false;
		}
		if (tile.getUnit()!=null) {
			return false;
		}
		return true;
	}
}
