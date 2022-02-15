package structures.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * A basic representation of of the Player. A player
 * has health and mana.
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class Player {

	int health;
	int mana;
	public List<Card> cardsInDeck;
	public List<Card> cardsInHand;
	public List<Unit> units;
	
	public Player() {
		super();
		this.health = 20;
		this.mana = 0;
		cardsInDeck = new ArrayList<Card>(20);
	}
	public Player(int health, int mana) {
		super();
		this.health = health;
		this.mana = mana;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	
	
	
}
