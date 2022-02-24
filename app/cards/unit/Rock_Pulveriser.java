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
public class Rock_Pulveriser extends UnitCard{
	
	public Rock_Pulveriser() {
		super();
		manacost = 2;
		// attack = 1;
		// health = 4;
		// ownername = "aiPlayer";
	}
	
	public Rock_Pulveriser(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
}

