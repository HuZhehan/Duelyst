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
public class Pyromancer extends UnitCard{
	
	public Pyromancer() {
		super();
		manacost = 2;
		// attack = 2;
		// health = 1;
		// ownername = "aiPlayer";
	}
	
	public Pyromancer(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
}

