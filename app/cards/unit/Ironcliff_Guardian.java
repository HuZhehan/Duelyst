package cards.unit;

import akka.actor.ActorRef;
import cards.UnitCard;
import structures.GameState;
import structures.basic.*;

/** 
 * UnitCard class of Ironcliff_Guardian. 
 * @author Student. Zhehan Hu
 */
public class Ironcliff_Guardian extends UnitCard{
	
	public Ironcliff_Guardian() {
		super();
		manacost = 5;
		// attack = 3;
		// health = 10;
		// ownername = "HumanPlayer";
	}
	
	public Ironcliff_Guardian(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
	
	// Check ability: Air drop: Can be summoned anywhere on the board.
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
