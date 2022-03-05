package cards.unit;

import akka.actor.ActorRef;
import cards.UnitCard;
import structures.GameState;
import structures.basic.*;

/** 
 * UnitCard class of Planar_Scout
 * Ability: Air drop: Can be summoned anywhere on the board.
 * @author Student. Zhehan Hu
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

