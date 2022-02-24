package structures.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import akka.actor.ActorRef;
import commands.BasicCommands;
import events.PreviousEvent;
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
	protected int attack;
	protected int health;
	protected int maxHealth;
	
	protected int maxMoveChance;
	protected int maxAttackChance;
	protected int moveChance;
	protected int attackChance;
	
	protected boolean summoned;
	protected boolean dead;
	protected String unitname;
	protected String ownername;
	
	// skill tags
	public boolean provoke;
	
	@JsonIgnore
	protected static ObjectMapper mapper = new ObjectMapper(); // Jackson Java Object Serializer, is used to read java objects from a file
	protected int id;
	protected UnitAnimationType animation;
	protected Position position;
	protected UnitAnimationSet animations;
	protected ImageCorrection correction;
	
	public Unit() {
		maxMoveChance = 1;
		maxAttackChance = 1;
		moveChance = 0;
		attackChance = 0;
		summoned = false;
		dead = false;
		
		// skills tags
		provoke = false;
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
	public void setName(String unitname) {
		this.unitname = unitname;
	}
	public int getMaxHealth(){
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth){
		this.maxHealth = maxHealth;
	}
	public int getHealth(){
		return health;
	}
	public void setHealth(int health) {
		if (health>this.maxHealth){
			health = this.maxHealth;
		}
		this.health = health;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public String getOwner() {
		return ownername;
	}
	public void setOwner(String ownername) {
		this.ownername = ownername;
	}
	// @author Student Zhehan Hu
	public void takeDamage(ActorRef out, GameState gameState, int damage) {
		// update states
		int hp = this.health - damage;
		this.setHealth(hp);
		// play animation
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.hit);
		BasicCommands.setUnitHealth(out, this, health);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.idle);
		// check death
		if (this.health<=0) {
			this.die(out, gameState);
		}
		
	}
	// @author Student Zhehan Hu
	public void takeHeal(ActorRef out, GameState gameState, int heal) {
		// update states
		this.setHealth(this.health + heal);
		// play animation
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.channel);
		BasicCommands.setUnitHealth(out, this, health);
		try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.idle);
	}
	// @author Student Zhehan Hu
	public void moveAttack(ActorRef out, GameState gameState, Unit target) {
		// move to a nearest tile
		// coordinate of target
		int x = target.getPosition().getTilex();
		int y = target.getPosition().getTiley();
		//int delay = 0;
		trytomove:
		for (int i=0;i<9;i++) {
			for (int j=0;j<5;j++) {
				Tile SpringBoard = gameState.tile[i][j];
				if (Math.pow((x-i),2)+Math.pow(y-j,2)<=2) {
					if (this.checkMove(out, gameState, SpringBoard)==true) {
						// gameState.previousEvent=PreviousEvent.block;
						this.move(out, gameState, SpringBoard);
						// delay = (Math.abs(x-i)+Math.abs(y-j))*1000;
						break trytomove;
						// BasicCommands.addPlayer1Notification(out, "fail to find the path", 20);
					}
				}
			}
		}
		// attack
		//try {Thread.sleep(delay);} catch (InterruptedException e) {e.printStackTrace();}
		this.attack(out, gameState, target);
	}
	// @author Student Zhehan Hu
	public void move(ActorRef out, GameState gameState, Tile destination) {
		// unit position
		int x = this.getPosition().getTilex();
		int y = this.getPosition().getTiley();
		// destination position
		int m = destination.getTilex();
		int n = destination.getTiley();
		// remove unit from old tile
		Tile origin = gameState.tile[x][y];
		origin.setUnit(null);
		// add unit to destination tile
		this.setPositionByTile(destination);
		destination.setUnit(this);
		// update states
		this.moveChance --;
		// play animation
		BasicCommands.moveUnitToTile(out, this, destination);
		int delay = (Math.abs(x-m)+Math.abs(y-n))*800;
		try {Thread.sleep(delay);} catch (InterruptedException e) {e.printStackTrace();}
		
		
	}
	// @author Student Zhehan Hu
	public void attack(ActorRef out, GameState gameState, Unit target) {
		// play animation
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.attack);
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.idle);
		// update states
		target.takeDamage(out, gameState, this.attack);
		this.moveChance --;
		this.attackChance --;
		// counter-attack
		if (target.getHealth()>0) {
			target.counterAttack(out, gameState, this);
		}
		
	}
	// @author Student Zhehan Hu
	public void counterAttack(ActorRef out, GameState gameState, Unit target) {
		// play animation
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.attack);
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.idle);
		// update states
		target.takeDamage(out, gameState, this.attack);
		
	}

	// @author Student Zhehan Hu
	public void enableMoveAttack() {
		this.moveChance = this.maxMoveChance;
		this.attackChance = this.maxAttackChance;
	}
	// @author Student Zhehan Hu
	public boolean check(ActorRef out, GameState gameState, Tile tile) {
		if (this.attackChance<=0) {
			return false;
		}
		if (this.moveChance<=0&&this.attackChance>0){
			if (this.checkAttack(out, gameState, tile)==true){
				return true;
			}
		}
		if (this.moveChance>0&&this.attackChance>0){
			// check Attack first, provoked unit cannot Move-Attack
			if (this.checkAttack(out, gameState, tile)==true){
				return true;
			}
			if (this.checkMoveAttack(out, gameState, tile)==true){
				return true;
			}
		}
		return false; 
	}
	public boolean checkMoveAttack(ActorRef out, GameState gameState, Tile tile) {
		// tile is empty, check if unit can move to
		if(tile.getUnit()==null) {
			return this.checkMove(out, gameState, tile);
		}
		// tile has enemy check if unit can attack
		if(tile.getUnit()!=null&&tile.getUnit().getOwner()!=this.getOwner()) {
			// for any springBoard tile where unit can move to , create a fake unit on it, 
			// then check if fake unit can attack the target on this springBoard tile,.
			// if so, this unit can move-attack target
			for (int i=0;i<9;i++) {
				for (int j=0;j<5;j++) {
					Tile springBoard = gameState.tile[i][j];
					Unit fake = new Unit();
					fake.setOwner(this.getOwner());
					fake.setPositionByTile(springBoard);
					if (this.checkMove(out, gameState, springBoard)==true&&fake.checkAttack(out, gameState, tile)==true) {
						return true;
					}
				}
			}
		}
		return false; 
	}
	//
	public boolean checkMove(ActorRef out, GameState gameState, Tile tile) {
		// tile is not empty, return false
		if(tile.getUnit()!=null) {
			return false;
		}
		
		// if target doesnt has provoke but another enemy in range has, return false
		if(this.checkProvoked(out, gameState)==true) {
			return false;
		}
		
		// coordinate of tile to check
		int x = tile.getTilex();
		int y = tile.getTiley();
		// coordinate of unit position
		int m = this.getPosition().getTilex();
		int n = this.getPosition().getTiley();

		// if tile is empty and is in unit's move range, it may be valid to move
		//   o o o
		//   o x o
		//   o o o
		if (tile.getUnit()==null && Math.pow((x-m),2)+Math.pow(y-n,2)<=2) {
			return true;
		}
		//     o
		//   x x x 
		// o x x x o
		//   x x x  
		//     o
		if (tile.getUnit()==null && Math.pow((x-m),2)+Math.pow(y-n,2)==4) {
			// check no enemy block between unit and tile
			//     o
			//   x e x
			// o e x e o
			//   x e x
			//     o
			Tile blockTile = gameState.tile[(x+m)/2][(y+n)/2];
			if (blockTile.getUnit()!=null&&blockTile.getUnit().getOwner()!=this.getOwner()){
				return false;
			}
			return true;
		}
		return false;
	}
	public boolean checkAttack(ActorRef out, GameState gameState, Tile tile) {
		// tile is empty or is friend unit, return false
		if(tile.getUnit()==null||tile.getUnit().getOwner()==this.getOwner()) {
			return false;
		}

		// if target doesn't has provoke but another enemy in range has, return false
		if(tile.getUnit().provoke==false && this.checkProvoked(out, gameState)==true) {
			return false;
		}
		
		// coordinate of target to check
		int x = tile.getTilex();
		int y = tile.getTiley();
		// coordinate of unit tile
		int m = this.getPosition().getTilex();
		int n = this.getPosition().getTiley();
		// if tile has enemy and is in unit's attack range, it may be valid to attack
		//   o o o
		//   o x o
		//   o o o
		if (Math.pow((x-m),2)+Math.pow(y-n,2)<=2) {
			return true;
		}
		return false;
	}
	// if enemy unit with provoke in adjacent tile, return true
	public boolean checkProvoked(ActorRef out, GameState gameState) {
		int m = this.getPosition().getTilex();
		int n = this.getPosition().getTiley();
		for (int i=0;i<9;i++) {
			for (int j=0;j<5;j++) {
				Tile provokeTile = gameState.tile[i][j];
				// we only check adjacent tiles, do not code 'if provokeTile.checkAttack(out,gameState, provokeTile)==true'
				// because some units have ability "ranged"
				if (provokeTile.getUnit()!=null&&provokeTile.getUnit().provoke==true) {
					if (Math.pow((i-m),2)+Math.pow(j-n,2)<=2) {
						return true;
					}
				}
			}
		}
		return false;
	}
	// @author Student Reetu Varadhan
	// @author Student Zhehan Hu
	public void die(ActorRef out, GameState gameState) {
		// play animation
		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.death);
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		// destroy Unit & reset tile
		this.dead = true;
		BasicCommands.deleteUnit(out, this);
		int x = getPosition().getTilex();
		int y = getPosition().getTiley();
		gameState.tile[x][y].setUnit(null);
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
