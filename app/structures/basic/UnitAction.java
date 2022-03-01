package structures.basic;

import akka.actor.ActorRef;
import structures.GameState;

/**
 * Interface of Unit class,
 * it contains actions a Unit object can play, 
 * setters and getters are not included.
 * @editor Student. Zhehan Hu
 * @author Student. Reetu Varadhan
 * 
 */
public interface UnitAction {
	
	/** 
	 * Unit takes damage and loses hp,
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 * @param damage number
	 */
	public void takeDamage(ActorRef out, GameState gameState, int damage);
	
	/** 
	 * Unit gets heal and recovers hp.
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 * @param heal number
	 */
	public void takeHeal(ActorRef out, GameState gameState, int heal);
	
	/** 
	 * Unit move-attack,
	 * will firstly move to a nearest tile where can attack,
	 * then attack.
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 * @param target unit
	 */
	public void moveAttack(ActorRef out, GameState gameState, Unit target);
	
	/** 
	 * Unit moves to a chosen tile,
	 * will move horizontally by default,
	 * or move vertically when horizontal way is blocked.
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 * @param destination tile
	 */
	public void move(ActorRef out, GameState gameState, Tile destination);
	
	/** 
	 * Unit attacks an enemy target,
	 * it triggers counter-attack if available
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 * @param target unit
	 */
	public void attack(ActorRef out, GameState gameState, Unit target);
	
	/** 
	 * Unit counter-attack after attacked,
	 * it will NOT trigger another counter-attack.
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 * @param target unit
	 */
	public void counterAttack(ActorRef out, GameState gameState, Unit target);
	
	/** 
	 * Unit' move and attack chance is reset to max
	 * @author Student. Zhehan Hu
	 */
	public void enableMoveAttack();
	
	/** 
	 * Check if this unit can play action on input tile, return true if tile is valid.
	 * It will check unit's move and attack chance left.
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 * @param tile to check
	 * @return boolean whether tile is valid
	 */
	public boolean check(ActorRef out, GameState gameState, Tile tile);
	
	/** 
	 * Check if this unit can move-attack unit on input tile, return true if tile is valid.
	 * It will check distance between unit and target tile.
	 * It will NOT check unit's move and attack chance left.
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 * @param tile to check
	 * @return boolean whether tile is valid
	 */
	public boolean checkMoveAttack(ActorRef out, GameState gameState, Tile tile);
	
	/** 
	 * Check if this unit can move to input tile, return true if tile is valid.
	 * It will check distance between unit and target tile.
	 * It will NOT check unit's move and attack chance left.
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 * @param tile to check
	 * @return boolean whether tile is valid
	 */
	public boolean checkMove(ActorRef out, GameState gameState, Tile tile);
	
	/** 
	 * Check if this unit can attack unit on input tile, return true if tile is valid.
	 * It will check distance between unit and target tile.
	 * It will NOT check unit's move and attack chance left.
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 * @param tile to check
	 * @return boolean whether tile is valid
	 */
	public boolean checkAttack(ActorRef out, GameState gameState, Tile tile);
	
	/** 
	 * Check if this unit is provoked, return true if it is.
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 * @return boolean whether unit is provoked
	 */
	public boolean checkProvoked(ActorRef out, GameState gameState);
	
	/** 
	 * Unit dies and be removed from game.
	 * @author Student. Reetu Varadhan
	 */
	public void die(ActorRef out, GameState gameState);

}
