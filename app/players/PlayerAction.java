package players;

import akka.actor.ActorRef;
import structures.GameState;
import structures.basic.Tile;
import structures.basic.Unit;

public interface PlayerAction {
	/**
	 *summon a unit according to id
	 * @author Student Zhehan Hu
	 * @param id, unit.id = card.id
	 * @param tile, where to summon
	 */
	public Unit summon(ActorRef out, GameState gameState, int id, Tile tile);
	public String getName();
	public void useCard(ActorRef out, GameState gameState, int id, Tile tile);
	
}
