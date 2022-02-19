package structures.basic;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import commands.BasicCommands;
import players.PlayerAction;
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
	protected String name;
	public List<Card> deck;
	public List<Card> hand;
	public List<Unit> army;
	
	protected int health;
	protected int mana;
	
	public Player() {
		deck = new ArrayList<Card>(20);
		hand = new ArrayList<Card>(10);
		army = new ArrayList<Unit>(10);
		
		this.health = 20;
		this.mana = 0;
	}
	public Player(int health, int mana) {
		this();
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
	/**
	 *summon a unit according to id
	 * @author Student Zhehan Hu
	 * @param configFile, name of unit, not used
	 * @param id, unit.id = card.id
	 * @param tile, where to summon
	 */
	public Unit summon(ActorRef out, GameState gameState, int id, Tile tile) {
		for (Unit u : army) {
			if (u.getId()==id) {
				// update states
				u.setPositionByTile(tile); 
				tile.setUnit(u);
				u.setOwner(this.getName());
				// play animation
				BasicCommands.playEffectAnimation(out, BasicObjectBuilders.loadEffect(StaticConfFiles.f1_summon), tile);
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
				BasicCommands.drawUnit(out, u, tile);
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
				// update states
				u.setAttack(out, gameState, u.getAttack());
				u.setHealth(out, gameState, u.getMaxHealth());
				return u;
			}
		}
		return null;
	}
	public String getName() {
		return name;
	}
	public void useCard() {

	}
	
	
}
