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
public class Bloodshard_Golem extends UnitCard{
	
	public Bloodshard_Golem() {
		super();
		manacost = 3;
		// attack = 4;
		// health = 3;
		// ownername = "aiPlayer";
	}
	
	public Bloodshard_Golem(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
}

