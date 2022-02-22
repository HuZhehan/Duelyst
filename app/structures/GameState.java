package structures;

import akka.actor.ActorRef;
import commands.BasicCommands;
import events.PreviousEvent;
import players.AiPlayer;
import players.HumanPlayer;
import structures.basic.*;
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
	
	public int Round = 0;
	
	public HumanPlayer humanPlayer = new HumanPlayer();
	public AiPlayer aiPlayer = new AiPlayer();
	public Player player = humanPlayer; // current player
	
	public Tile[][] tile = new Tile[9][5];
	
	public PreviousEvent previousEvent = null;
	public Card previousCard = null;
	public Unit previousUnit = null;
	
	public GameState() {}
	
	public void createBoard(ActorRef out) {
		for (int i=0;i<9;i++) {
			for (int j=0;j<5;j++) {
				tile[i][j] = BasicObjectBuilders.loadTile(i, j);
					BasicCommands.drawTile(out, tile[i][j], 0);
			}
			try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}

	public void clear(ActorRef out) {
		// clear states
		previousEvent = null;
		previousCard = null;
		previousUnit = null;
		// clear board
		for (int i=0;i<9;i++) {
			for (int j=0;j<5;j++) {
				BasicCommands.drawTile(out, tile[i][j], 0);
			}
			try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
		}
		// clear card
		for (Card c : humanPlayer.hand) {
			BasicCommands.drawCard(out, c, humanPlayer.hand.indexOf(c)+1, 0);
		}
		try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();
		}
	}

	


	
	
	

}
