package players;

import java.util.ArrayList;
import java.util.List;
import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.basic.Card;
import structures.basic.Player;
import utils.OrderedCardLoader;

/** 
 * @author Zhehan Hu, 
 */

public class HumanPlayer extends Player {
	
	public HumanPlayer() {
		super();
		this.deck = OrderedCardLoader.getPlayer1Cards();
	}
	public HumanPlayer(int health, int mana) {
		super();
		this.deck = OrderedCardLoader.getPlayer2Cards();
	}
	/** 
	 * @drawCard() - player draw card method
	 * @param n - number of card to draw
	 * @param mode - animation type
	 */
	public void drawCard(ActorRef out, int n, int mode) {
		for (int i=0;i<n;i++) {
			// if no card in deck , game ends
			if (deck.size()==0) {
			// placeholder
			}else {
				Card card = deck.get(0);
				deck.remove(0);
				hand.add(card);
				BasicCommands.drawCard(out, card, hand.size(), mode);
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
				// max 6 cards due to UI limitation, discard
				if (hand.size()==7) {
					hand.remove(6);
					BasicCommands.deleteCard(out, hand.size());
					try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		}
	}
	public void setHealth(ActorRef out, int health) {
		this.health = health;
		BasicCommands.setPlayer1Health(out, this);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
	}
	public void setMana(ActorRef out, int mana) {
		this.mana = mana;
		BasicCommands.setPlayer1Mana(out, this);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
	}
}
