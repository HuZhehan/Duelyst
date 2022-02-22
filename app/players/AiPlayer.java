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
		name = "AiPlayer";
		deck = OrderedCardLoader.getPlayer2Cards();
		army = UnitLoader.getPlayer2Units();
	}
	public AiPlayer(int health, int mana) {
		super(health, mana);
	}

	/**
	 * draw card method
	 * @author Zhehan Hu, 
	 * @param n - number of card to draw
	 * @param mode - animation type
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
	/**
	 *
	 * @author Student Zhehan Hu
	 * @param
	 */
	public void discard(ActorRef out, GameState gameState, int index) {
		hand.remove(index);
	}
	/**
	 *
	 * @author Student Zhehan Hu
	 * @param
	 */
	public void useCard(ActorRef out, GameState gameState, int id, Tile tile) {
		for (Card c : hand) {
			if (c.getId()==id) {
				int index = this.hand.indexOf(c);
				int mana = this.getMana() - c.getManacost();
				this.setMana(mana);
				BasicCommands.setPlayer2Mana(out, this);
				this.discard(out, gameState, index);
				c.content(out, gameState, tile);
			}
		}
	}
}