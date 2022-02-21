package structures.basic;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import commands.BasicCommands;
import utils.BasicObjectBuilders;
import utils.OrderedCardLoader;
import utils.StaticConfFiles;

/**
 * A basic representation of of the Player. A player
 * has health and mana.
 * 
 * @author Dr. Richard McCreadie
 * @editor Student Zhehan Hu
 */
public class Player implements PlayerAction{
	// editor Zhehan hu
	public List<Card> deck;
	public List<Card> hand;
	public List<Unit> army;
	//
	protected int health;
	protected int mana;
	
	public Player() {
		super();
		this.health = 20;
		this.mana = 0;
		this.deck = new ArrayList<Card>();
		this.hand = new ArrayList<Card>();
		this.army = new ArrayList<Unit>();
	}
	public Player(int health, int mana) {
		super();
		this.health = health;
		this.mana = mana;
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
	//
	public Unit createUnit(ActorRef out, String configFile, int id, Tile tile, Class<? extends Unit> classType) {
		Unit unit = BasicObjectBuilders.loadUnit(configFile, id, classType);
		unit.setPositionByTile(tile); 
		//unit.setTile(tile);
		//tile.setUnit(unit);
		unit.setOwner(this);
		BasicCommands.playEffectAnimation(out, BasicObjectBuilders.loadEffect(StaticConfFiles.f1_summon), tile);
		BasicCommands.drawUnit(out, unit, tile);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		unit.setAttack(out, 2);
		unit.setHealth(out, 20);
		return unit;
	}
	public void useSpellCard() {

	}
	
	public void useUnitcard() {
		
	}
	
	
}
