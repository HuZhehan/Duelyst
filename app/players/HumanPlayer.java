package players;

import java.util.List;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.Card;
import structures.basic.Player;
import structures.basic.Tile;
import structures.basic.Unit;
import utils.BasicObjectBuilders;
import utils.OrderedCardLoader;
import utils.StaticConfFiles;
import utils.UnitLoader;

/** 
 * @author Zhehan Hu, 
 */

public class HumanPlayer extends Player {
		
	public HumanPlayer() {
		super();
		name = "HumanPlayer";
		deck = OrderedCardLoader.getPlayer1Cards();
		army = UnitLoader.getPlayer1Units();
	}
	public HumanPlayer(int health, int mana) {
		super(health, mana);
	}
	
}
