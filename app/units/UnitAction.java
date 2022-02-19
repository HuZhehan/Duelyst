package units;

import akka.actor.ActorRef;
import structures.GameState;
import structures.basic.Tile;
import structures.basic.Unit;

public interface UnitAction {
	// @author Student Zhehan Hu
	public String getName();
	public String getOwner();
	public int getMaxHealth();
	public int getHealth();
	public int getAttack();
	public void setHealth(ActorRef out, GameState gameState, int health);
	public void setAttack(ActorRef out, GameState gameState, int attack);
	public void setOwner(String owner);
	public void move(ActorRef out, GameState gameState, Tile destination);
	public void attack(ActorRef out, GameState gameState, Unit target);
	public void counterAttack(ActorRef out, GameState gameState, Unit target);
	public void die(ActorRef out, GameState gameState);
}
