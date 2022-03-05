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
 * Player class of AiPlayer,
 * it overrides methods from super Player class to remove animations.
 * @author Student. Zhehan Hu
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

	public void drawCard(ActorRef out, GameState gameState, int n) {
		for (int i=0;i<n;i++) {
			// game end if no card to draw
			if (deck.size()==0) {
				gameState.gameEnd(out);
			}else {
				// move card from deck to hand
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
				// decrease mana
				int mana = this.getMana() - c.getManacost();
				this.setMana(mana);
				BasicCommands.setPlayer2Mana(out, this);
				this.discard(out, gameState, index);
				// play action
				c.content(out, gameState, tile);
				
				// trigger spellThief
				// Ability: If the enemy player casts a spell, this minion gains +1 attack and +1 health
				if (c.getType()=="SpellCard") {
					for (int i=0;i<9;i++) {
						for (int j=0;j<5;j++) {
							if (gameState.tile[i][j].getUnit()!=null) {
								Unit unit = gameState.tile[i][j].getUnit();
								if (unit.spellThief==true&&unit.checkSkill(out, gameState, this)==true) {
									unit.useSkill(out, gameState);
								}
							}
						}
					}
				}
				break;
			}
		}
	}
}