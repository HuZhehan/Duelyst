package cards;

import akka.actor.ActorRef;
import structures.GameState;
import structures.basic.Tile;

public interface CardAction {
	public boolean prompt(ActorRef out, GameState gameState, Tile tile);
	public void act(ActorRef out, GameState gameState, Tile tile);
}
