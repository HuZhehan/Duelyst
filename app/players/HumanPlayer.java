package players;

import java.util.List;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.Card;
import structures.basic.Player;
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
	/** 
	 * @author Zhehan Hu,
	 * @drawCard() - player draw card
	 * @param n - number of card to draw
	 * @param mode - animation type
	 */
	public void drawCard(ActorRef out, GameState gameState, int n, int mode) {
		for (int i=0;i<n;i++) {
			if (deck.size()==0) {
				//placeholder
			}else {
				Card card = deck.get(0);
				deck.remove(0);
				hand.add(card);
				BasicCommands.drawCard(out, card, hand.size(), mode);
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
				// max 6 cards due to UI limitation, discard
				if (hand.size()==7) {
					hand.remove(6);
					BasicCommands.deleteCard(out, 7);
					try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		}
	}
	public void setHealth(ActorRef out, GameState gameState, int health) {
		if (health>20) {
			health = 20;
		}
		this.health = health;
		BasicCommands.setPlayer1Health(out, this);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		if (this.health<0) {
			//placeholder
		}
	}
	public void setMana(ActorRef out, GameState gameState, int mana) {
		if (mana>9) {
			mana = 9;
		}
		this.mana = mana;
		BasicCommands.setPlayer1Mana(out, this);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
	}
	public void useSpellCard(ActorRef out, GameState gameState, int id, Unit unit) {
		for (Card c : hand) {
			if (c.getId()==id) {
				
			}
		}
	}
}
