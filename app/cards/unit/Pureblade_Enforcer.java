package cards.unit;

import akka.actor.ActorRef;
import cards.UnitCard;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

/** 
 * UnitCard class of Pureblade_Enforcer
 * @author Student. Zhehan Hu
 */
public class Pureblade_Enforcer extends UnitCard{
	
	public Pureblade_Enforcer() {
		super();
		manacost = 2;
		// attack = 1;
		// health = 4;
		// ownername = "HumanPlayer";
	}
	
	public Pureblade_Enforcer(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
}
