package cards.unit;

import akka.actor.ActorRef;
import cards.UnitCard;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

/** 
 * UnitCard class of Silverguard_Knight
 * @author Student. Zhehan Hu
 */
public class Silverguard_Knight extends UnitCard{
	
	public Silverguard_Knight() {
		super();
		manacost = 3;
		// attack = 1;
		// health = 5;
		// ownername = "HumanPlayer";
	}
	
	public Silverguard_Knight(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
}

