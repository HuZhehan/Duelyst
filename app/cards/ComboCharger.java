package cards;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

public class ComboCharger extends UnitCard{
	
	public ComboCharger() {
		super();
		cardname = "ComboCharger";
		manacost = 1;
		ownername = "HumanPlayer";
	}
	
	public ComboCharger(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
}
