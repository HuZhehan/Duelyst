package cards.unit;

import akka.actor.ActorRef;
import cards.UnitCard;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

/** 
 * UnitCard class of Combo_Charger
 * @author Student. Zhehan Hu
 */
public class Combo_Charger extends UnitCard{
	
	public Combo_Charger() {
		super();
		manacost = 1;
	    // attack = 1;
		// health = 3;
		// ownername = "HumanPlayer";
	}
	
	public Combo_Charger(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
}
