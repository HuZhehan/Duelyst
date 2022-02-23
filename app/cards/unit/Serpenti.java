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
public class Serpenti extends UnitCard{
	
	public Serpenti() {
		super();
		manacost = 6;
		attack = 7;
		health = 4;
		ownername = "aiPlayer";
	}
	
	public Serpenti(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
	
}

