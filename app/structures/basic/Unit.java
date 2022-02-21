package structures.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import akka.actor.ActorRef;
import commands.BasicCommands;
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

	@JsonIgnore
	protected static ObjectMapper mapper = new ObjectMapper(); // Jackson Java Object Serializer, is used to read java objects from a file
	// editor Zhehan hu
	protected int health;
	protected int attack;
	protected boolean moveable;
	protected boolean attackable;
	protected boolean dead;
	protected Player owner;
	protected Tile tile;
	//
	protected int id;
	protected UnitAnimationType animation;
	protected Position position;
	protected UnitAnimationSet animations;
	protected ImageCorrection correction;
	
	public Unit() {}
	
	public Unit(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super();
		this.health = 0;
		this.attack = 0;
		this.moveable = true;
		this.attackable = true;
		this.dead = false;
		this.owner = null;
		
		this.id = id;
		this.animation = UnitAnimationType.idle;
		
		position = new Position(0,0,0,0);
		this.correction = correction;
		this.animations = animations;
	}
	
	public Unit(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		super();
		this.health = 0;
		this.attack = 0;
		this.moveable = true;
		this.attackable = true;
		this.dead = false;
		this.owner = null;

		this.id = id;
		this.animation = UnitAnimationType.idle;
		
		position = new Position(currentTile.getXpos(),currentTile.getYpos(),currentTile.getTilex(),currentTile.getTiley());
		this.correction = correction;
		this.animations = animations;
	}
	
	public Unit(int id, UnitAnimationType animation, Position position, UnitAnimationSet animations,
			ImageCorrection correction) {
		super();
		this.health = 0;
		this.attack = 0;
		this.moveable = true;
		this.attackable = true;
		this.dead = false;
		this.owner = null;
		
		this.id = id;
		this.animation = animation;
		this.position = position;
		this.animations = animations;
		this.correction = correction;
	}
	
	// @author Zhehan Hu
	public int getHealth(){
		return health;
	}
	public void setHealth(ActorRef out, int health) {
		this.health = health;
		BasicCommands.setUnitHealth(out, this, health);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		if (this.health<0) {
			this.die(out);
		}
	}
	// @author Zhehan Hu
	public int getAttack() {
		return attack;
	}
	public void setAttack(ActorRef out, int attack) {
		this.attack = attack;
		BasicCommands.setUnitAttack(out, this, attack);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
	}
	// @author Zhehan Hu
	public Player getOwner() {
		return owner;
	}
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	// @author Zhehan Hu
	public void move(ActorRef out, Tile origin, Tile destination) {
		origin.setUnit(null);
		BasicCommands.moveUnitToTile(out, this, destination);
		try {Thread.sleep(6000);} catch (InterruptedException e) {e.printStackTrace();}
		this.setPositionByTile(destination);
		destination.setUnit(this);
	}
	// @author Zhehan Hu
	public void attack(ActorRef out, Unit target) {
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.attack);
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		int hp = target.getHealth() - attack;
		target.setHealth(out, hp);
		if (target.getHealth()>0) {
			target.counterAttack(out,this);
		}
		this.moveable = false;
		this.attackable = false;
	}
	public void counterAttack(ActorRef out, Unit target) {
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.attack);
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		int hp = target.getHealth() - attack;
		target.setHealth(out, hp);
	}
	// @author Zhehan Hu
	public void die(ActorRef out) {
		
	}
	
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
