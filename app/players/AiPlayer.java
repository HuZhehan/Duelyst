package players;

import java.util.ArrayList;
import java.util.List;
import akka.actor.ActorRef;
import commands.BasicCommands;
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

public class AiPlayer extends Player {
	
	public AiPlayer() {
		super();
		this.deck = OrderedCardLoader.getPlayer2Cards();
	}
	public AiPlayer(int health, int mana) {
		super();
		this.deck = OrderedCardLoader.getPlayer2Cards();
		this.army = UnitLoader.getPlayer2Units();
	}
	
	/**
	 * @author Zhehan Hu, 
	 * @drawCard() - player draw card method
	 * @param n - number of card to draw
	 * @param mode - animation type, not used
	 */
	public void drawCard(ActorRef out, int n, int mode) {
		for (int i=0;i<n;i++) {
			// this.end
			if (deck.size()==0) {
			// placeholder
			}else {
				Card card = deck.get(0);
				deck.remove(0);
				hand.add(card);
				// max 6 cards due to UI limitation, discard
				if (hand.size()==7) {
					hand.remove(6);
				}
			}
		}
	}
	public void setHealth(ActorRef out, int health) {
		this.health = health;
		BasicCommands.setPlayer2Health(out, this);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
	}
	public void setMana(ActorRef out, int mana) {
		this.mana = mana;
		BasicCommands.setPlayer2Mana(out, this);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
	}
}