package players;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.Card;
import structures.basic.Player;
import structures.basic.Tile;
import structures.basic.Unit;
import utils.OrderedCardLoader;
import utils.UnitLoader;

/**
 * @author Zhehan Hu, 
 */

public class AiPlayer extends Player {
	
	public AiPlayer() {
		super();
		name = "AiPlayer";
		deck = OrderedCardLoader.getPlayer2Cards();
		army = UnitLoader.getPlayer2Units();
	}
	public AiPlayer(int health, int mana) {
		super(health, mana);
	}
	/**
	 * @author Zhehan Hu, 
	 * @drawCard() - player draw card method
	 * @param n - number of card to draw
	 * @param mode - animation type, not used
	 */
	public void drawCard(ActorRef out, GameState gameState, int n, int mode) {
		for (int i=0;i<n;i++) {
			if (deck.size()==0) {
				// placeholder
			}else {
				Card card = deck.get(0);
				deck.remove(0);
				hand.add(card);
				card.setOwner(this.getName());
				// max 6 cards due to UI limitation, discard
				if (hand.size()==7) {
					discard(out, gameState, 6);
				}
			}
		}
	}
	public void discard(ActorRef out, GameState gameState, int index) {
		hand.remove(index);
	}
	public void useCard(ActorRef out, GameState gameState, int id, Tile tile) {
		for (Card c : hand) {
			if (c.getId()==id) {
				int index = this.hand.indexOf(c);
				int mana = this.getMana() - c.getManacost();
				this.setMana(out, gameState, mana);
				this.discard(out, gameState, index);
				c.content(out, gameState, tile);
				
			}
		}
	}
	public void setHealth(ActorRef out, GameState gameState, int health) {
		if (health>20) {
			health = 20;
		}
		this.health = health;
		BasicCommands.setPlayer2Health(out, this);
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
		BasicCommands.setPlayer2Mana(out, this);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
	}
}