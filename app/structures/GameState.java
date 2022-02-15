package structures;

import structures.basic.Player;
import structures.basic.Tile;
import utils.BasicObjectBuilders;

/**
 * This class can be used to hold information about the on-going game.
 * Its created with the GameActor.
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class GameState {

	
	public boolean gameInitalised = false;
	public boolean something = false;
	
	public int gameRound = 0;
	
	public static Player humanPlayer = new Player(20, 0);	
	public static Player aiPlayer = new Player(20, 0);	
	
	public static Tile[][] tile = new Tile[10][6];

	
	
	
	

}
