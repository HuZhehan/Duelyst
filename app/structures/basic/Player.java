package structures.basic;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import units.HumanAvatar;
import utils.BasicObjectBuilders;
import utils.OrderedCardLoader;
import utils.StaticConfFiles;
import utils.UnitLoader;

/**
 * A basic representation of of the Player. A player
 * has health and mana.
 * 
 * @author Dr. Richard McCreadie
 * @author Student Zhehan Hu
 */
public class Player implements PlayerAction{
	// @author Student Zhehan Hu
	public List<Card> deck;
	public List<Card> hand;
	public List<Unit> army;
	//
	protected int health;
	protected int mana;
	protected String name;
	
	public Player() {
		super();
		this.health = 20;
		this.mana = 0;
		this.name = "Player";
		this.deck = new ArrayList<Card>();
		this.hand = new ArrayList<Card>();
		this.army = new ArrayList<Unit>();
	}
	public Player(int health, int mana) {
		super();
		this.health = health;
		this.mana = mana;
		this.name = "Player";
		this.deck = new ArrayList<Card>();
		this.hand = new ArrayList<Card>();
		this.army = new ArrayList<Unit>();
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
	// @author Student Zhehan Hu
	public String getName() {
		return name;
	}
	/**
	 *summon a unit according to id
	 * @author Student Zhehan Hu
	 * @param configFile, name of unit, not used
	 * @param id, unit.id = card.id
	 * @param tile, where to summon
	 */
	public Unit summon(ActorRef out, GameState gameState, int id, Tile tile) {
		for (Unit i : army) {
			if (i.getId()==id) {
				// update states
				i.setPositionByTile(tile); 
				tile.setUnit(i);
				i.setOwner(this.getName());
				// play animation
				BasicCommands.playEffectAnimation(out, BasicObjectBuilders.loadEffect(StaticConfFiles.f1_summon), tile);
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
				BasicCommands.drawUnit(out, i, tile);
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
				// update states
				i.setAttack(out, gameState, i.getAttack());
				i.setHealth(out, gameState, i.getMaxHealth());
				return i;
			}
		}
		return null;
	}
	
	public void useSpellCard() {

	}
	
	public void useUnitcard() {
		
	}
	
	
}
