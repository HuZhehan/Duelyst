package structures.basic;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import units.Human_Avatar;
import utils.BasicObjectBuilders;
import utils.OrderedCardLoader;
import utils.StaticConfFiles;
import utils.UnitLoader;

/**
 * A basic representation of of the Player. A player
 * has health and mana.
 * 
 * @author Dr. Richard McCreadie
 * @author Student. Zhehan Hu
 */
public class Player implements PlayerAction{
	
	protected String name;
	public List<Card> deck;
	public List<Card> hand;
	public List<Unit> army;
	public List<Unit> summoned;
	
	protected int health;
	protected int mana;
	
	public Player() {
		deck = new ArrayList<Card>(20);
		hand = new ArrayList<Card>(10);
		army = new ArrayList<Unit>(20);
		summoned = new ArrayList<Unit>(10);
		
		this.health = 20;
		this.mana = 0;
	}
	public Player(int health, int mana) {
		this();
		this.health = health;
		this.mana = mana;
	}
	
	public void drawCard(ActorRef out, GameState gameState, int number) {
		for (int i=0;i<number;i++) {
			// game end if no card to draw
			if (deck.size()==0) {
				gameState.gameEnd(out);
			}else {
				// move card from deck to hand
				Card card = deck.get(0);
				deck.remove(0);
				hand.add(card);
				card.setOwner(this.getName());
				BasicCommands.drawCard(out, card, hand.size(), 0);
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
				// max 6 cards due to UI limitation, discard
				if (hand.size()==7) {
					discard(out, gameState, 6);
				}
			}
		}
	}
	public void discard(ActorRef out, GameState gameState, int index) {
		hand.remove(index);
		BasicCommands.deleteCard(out, index+1);
		try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
		// highlight refresh
		BasicCommands.deleteCard(out, hand.size()+1);
		for (Card c : hand) {
			BasicCommands.drawCard(out, c, hand.indexOf(c)+1, 0);
		}
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
	}
	public void summon(ActorRef out, GameState gameState, int id, Tile tile) {
		for (Unit u : army) {
			if (u.getId()==id) {
				// update states
				u.setPositionByTile(tile); 
				u.setOwner(this.getName());
				tile.setUnit(u);
				this.summoned.add(u);
				this.army.remove(u);
				// play animation
				BasicCommands.playEffectAnimation(out, BasicObjectBuilders.loadEffect(StaticConfFiles.f1_summon), tile);
				BasicCommands.drawUnit(out, u, tile);
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
				BasicCommands.setUnitAttack(out, u, u.getAttack());
				BasicCommands.setUnitHealth(out, u, u.getMaxHealth());
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}				
				break;
			}
		}
	}
	public void useCard(ActorRef out, GameState gameState, int id, Tile tile) {
		for (Card c : hand) {
			if (c.getId()==id) {
				int index = this.hand.indexOf(c);
				// decrease mana
				int mana = this.getMana() - c.getManacost();
				this.setMana(mana);
				BasicCommands.setPlayer1Mana(out, this);
				this.discard(out, gameState, index);
				// play action
				c.content(out, gameState, tile);
				
				// trigger spellThief
				// If the enemy player casts a spell, this minion gains +1 attack and +1 health
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		if (health>20) {
			health = 20;
		}
		this.health = health;
	}
	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		if (mana>9) {
			mana = 9;
		}
		if (mana<0) {
			mana = 0;
		}
		this.mana = mana;
	}
}
