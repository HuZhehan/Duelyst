package cards;

import akka.actor.ActorRef;
import structures.GameState;
import structures.basic.Tile;

/** 
 * @author Zhehan Hu,
 */
public interface CardAction {
	public boolean check(ActorRef out, GameState gameState, Tile tile);
	public void content(ActorRef out, GameState gameState, Tile tile);
}
