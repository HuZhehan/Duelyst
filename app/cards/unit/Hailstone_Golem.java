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
public class Hailstone_Golem extends UnitCard{
	
	public Hailstone_Golem() {
		super();
		manacost = 4;
		// attack = 4;
		// health = 6;
		// ownername = "HumanPlayer";
		// ownername = "AiPlayer";
	}
	
	public Hailstone_Golem(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
}
