package structures.basic;

import akka.actor.ActorRef;
import structures.GameState;

/** 
 * Interface of Player class,
 * it contains actions a Player object can play, 
 * setters and getters are not included.
 * @author Student. Zhehan Hu
 */
public interface PlayerAction {
	
	/** 
	 * Draw the number of card from deck to hand,
	 * will check to discard every draw. 
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 * @param number of card to draw
	 */
	public void drawCard(ActorRef out, GameState gameState, int number);
	
	/**
	 * Discard a card in hand according to index.
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 * @param index of card in hand
	 */
	public void discard(ActorRef out, GameState gameState, int index);
	
	/**
	 * Summon a unit according to id,
	 * will remove unit in army List and add it to summoned List
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 * @param id of unit
	 * @param tile where to summon
	 */
	public void summon(ActorRef out, GameState gameState, int id, Tile tile);
	
	/**
	 * Use a card on chosen tile according to card id,
	 * for UnitCard, summon a unit on input tile, 
	 * for SpellCard, play affect on input tile.
	 * @author Student. Zhehan Hu
	 * @param
	 * @param out
	 * @param gameState
	 * @param id of card
	 * @param tile where to play card action
	 */
	public void useCard(ActorRef out, GameState gameState, int id, Tile tile);

}
