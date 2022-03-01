package structures.basic;

import akka.actor.ActorRef;
import structures.GameState;

/** 
 * Interface of Card class,
 * it contains actions a Card object can play, 
 * setters and getters are not included.
 * @author Student. Zhehan Hu
 */
public interface CardAction {
	
	/** 
	 * Check if this card be used on input tile, return true if tile is valid, 
	 * for UnitCard, check whether unit can be summoned on input tile, 
	 * for SpellCard, check whether affect can be applied on input tile.
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 * @param tile to check
	 * @return boolean whether tile is valid
	 */
	public boolean check(ActorRef out, GameState gameState, Tile tile);
	
	/** 
	 * Use this card on input tile, 
	 * for UnitCard, summon unit on input tile (unit id = card id), 
	 * for SpellCard, apply affect on input tile.
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 * @param tile to play action
	 */
	public void content(ActorRef out, GameState gameState, Tile tile);
}
