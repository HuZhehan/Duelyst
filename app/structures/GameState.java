package structures;

import akka.actor.ActorRef;
import commands.BasicCommands;
import players.AiPlayer;
import players.HumanPlayer;
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
	
	public int Round = 0;
	
	public HumanPlayer humanPlayer = new HumanPlayer();
	public AiPlayer aiPlayer = new AiPlayer();
	
	public Tile[][] tile = new Tile[9][5];
	
	public GameState() {}
	
	public void createBoard(ActorRef out) {
		for (int i=0;i<9;i++) {
			for (int j=0;j<5;j++) {
				tile[i][j] = BasicObjectBuilders.loadTile(i, j);
					BasicCommands.drawTile(out, tile[i][j], 0);
			}
		}
	}


	
	
	

}
