package structures.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import units.UnitAction;
import utils.BasicObjectBuilders;

/**
 * This is a representation of a Unit on the game board.
 * A unit has a unique id (this is used by the front-end.
 * Each unit has a current UnitAnimationType, e.g. move,
 * or attack. The position is the physical position on the
 * board. UnitAnimationSet contains the underlying information
 * about the animation frames, while ImageCorrection has
 * information for centering the unit on the tile. 
 * 
 * @author Dr. Richard McCreadie
 * @editor Student Zhehan Hu
 *
 */
public class Unit implements UnitAction{
	
	// @author Student Zhehan Hu
	protected int maxHealth;
	protected int health;
	protected int attack;
	protected boolean moveable;
	protected boolean attackable;
	protected boolean summoned;
	protected boolean dead;
	protected String unitname;
	protected String ownername;
	
	@JsonIgnore
	protected static ObjectMapper mapper = new ObjectMapper(); // Jackson Java Object Serializer, is used to read java objects from a file
	protected int id;
	protected UnitAnimationType animation;
	protected Position position;
	protected UnitAnimationSet animations;
	protected ImageCorrection correction;
	
	public Unit() {
		moveable = true;
		attackable = true;
		summoned = false;
		dead = false;
	}
	
	public Unit(int id, UnitAnimationSet animations, ImageCorrection correction) {
		this();
		this.id = id;
		this.animation = UnitAnimationType.idle;
		position = new Position(0,0,0,0);
		this.correction = correction;
		this.animations = animations;
	}
	
	public Unit(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		this();
		this.id = id;
		this.animation = UnitAnimationType.idle;
		position = new Position(currentTile.getXpos(),currentTile.getYpos(),currentTile.getTilex(),currentTile.getTiley());
		this.correction = correction;
		this.animations = animations;
	}
	
	public Unit(int id, UnitAnimationType animation, Position position, UnitAnimationSet animations, ImageCorrection correction) {
		this();
		this.id = id;
		this.animation = animation;
		this.position = position;
		this.animations = animations;
		this.correction = correction;
	}
	// @author Student Zhehan Hu
	public String getName() {
		return unitname;
	}
	public int getMaxHealth(){
		return maxHealth;
	}
	public int getHealth(){
		return health;
	}
	public void setHealth(ActorRef out, GameState gameState, int health) {
		this.health = health;
		BasicCommands.setUnitHealth(out, this, health);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		if (this.health<0) {
			this.die(out, gameState);
		}
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(ActorRef out, GameState gameState, int attack) {
		this.attack = attack;
		BasicCommands.setUnitAttack(out, this, attack);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
	}
	public String getOwner() {
		return ownername;
	}
	public void setOwner(String owner) {
		this.ownername = owner;
	}
	public Player getPlayer(ActorRef out, GameState gameState) {
		if (ownername == "HumanPlayer") {
			return gameState.humanPlayer;
		}
		if (ownername == "AiPlayer") {
			return gameState.aiPlayer;
		}
		return null;
	}
	// @author Student Zhehan Hu
	public void move(ActorRef out, GameState gameState, Tile destination) {
		// remove unit from old tile
		int x = this.getPosition().getTilex();
		int y = this.getPosition().getTiley();
		Tile origin = gameState.tile[x][y];
		origin.setUnit(null);
		// play animation
		BasicCommands.moveUnitToTile(out, this, destination);
		try {Thread.sleep(6000);} catch (InterruptedException e) {e.printStackTrace();}
		// add unit to destination tile
		this.setPositionByTile(destination);
		destination.setUnit(this);
		// update states
		this.moveable = false;
	}
	// @author Student Zhehan Hu
	public void attack(ActorRef out, GameState gameState, Unit target) {
		// play animation
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.attack);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		// update states
		int hp = target.getHealth() - attack;
		target.setHealth(out, gameState, hp);
		this.moveable = false;
		this.attackable = false;
		// counter-attack
		if (target.getHealth()>0) {
			target.counterAttack(out, gameState, this);
		}
		/*
		// re-draw unit, otherwise animation stops
		int x = this.getPosition().getTilex();
		int y = this.getPosition().getTiley();
		Tile tile = gameState.tile[x][y];
		BasicCommands.drawUnit(out, this, tile);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		*/
	}
	public void counterAttack(ActorRef out, GameState gameState, Unit target) {
		// play animation
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.attack);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		// update states
		int hp = target.getHealth() - attack;
		target.setHealth(out, gameState, hp);
		/*
		// re-draw unit, otherwise animation stops
		int x = this.getPosition().getTilex();
		int y = this.getPosition().getTiley();
		Tile tile = gameState.tile[x][y];
		BasicCommands.drawUnit(out, this, tile);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		*/
	}
	public void die(ActorRef out, GameState gameState) {
		
	}
	//
	public String toString() {
		return "Unit";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UnitAnimationType getAnimation() {
		return animation;
	}
	public void setAnimation(UnitAnimationType animation) {
		this.animation = animation;
	}

	public ImageCorrection getCorrection() {
		return correction;
	}

	public void setCorrection(ImageCorrection correction) {
		this.correction = correction;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public UnitAnimationSet getAnimations() {
		return animations;
	}

	public void setAnimations(UnitAnimationSet animations) {
		this.animations = animations;
	}
	
	/**
	 * This command sets the position of the Unit to a specified
	 * tile.
	 * @param tile
	 */
	@JsonIgnore
	public void setPositionByTile(Tile tile) {
		position = new Position(tile.getXpos(),tile.getYpos(),tile.getTilex(),tile.getTiley());
	}
	
	
}
