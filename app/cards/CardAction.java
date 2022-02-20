package cards;

import akka.actor.ActorRef;
import structures.GameState;
import structures.basic.Tile;

/** 
 * @author Zhehan Hu,
 */
public interface CardAction {
	public boolean prompt(ActorRef out, GameState gameState, Tile tile);
	public void content(ActorRef out, GameState gameState, Tile tile);
}
