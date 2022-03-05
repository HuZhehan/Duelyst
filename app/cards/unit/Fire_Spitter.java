package cards.unit;

import akka.actor.ActorRef;
import cards.UnitCard;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

/** 
 * UnitCard class of Fire_Spitter
 * @author Student. Zhehan Hu
 */
public class Fire_Spitter extends UnitCard{
	
	public Fire_Spitter() {
		super();
		manacost = 4;
		// attack = 3;
		// health = 2;
		// ownername = "HumanPlayer";
	}
	
	public Fire_Spitter(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
}
