package units;

import akka.actor.ActorRef;
import structures.GameState;
import structures.basic.Tile;
import structures.basic.Unit;
// @author Student Zhehan Hu
public interface UnitAction {

	public String getName();
	public String getOwner();
	public int getMaxHealth();
	public int getHealth();
	public int getAttack();
	public void setHealth(int health);
	public void setAttack(int attack);
	public void setOwner(String owner);
	public void move(ActorRef out, GameState gameState, Tile destination);
	public void attack(ActorRef out, GameState gameState, Unit target);
	public void counterAttack(ActorRef out, GameState gameState, Unit target);
	public void die(ActorRef out, GameState gameState);
}
