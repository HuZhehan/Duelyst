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
public class Azurite_Lion extends UnitCard{
	
	public Azurite_Lion() {
		super();
		manacost = 3;
		// attack = 2;
		// health = 3;
		// ownername = "HumanPlayer";
	}
	
	public Azurite_Lion(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
}
