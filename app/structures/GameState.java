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
	
	public void humanTurnStart(ActorRef out) {
		BasicCommands.addPlayer1Notification(out, "Round"+Integer.toString(Round), 2);
		player = humanPlayer;
		humanPlayer.setMana(Round+1);
		BasicCommands.setPlayer1Mana(out, humanPlayer);
		for (Unit u:humanPlayer.summoned) {
			u.enableMoveAttack();
		}
	}
	
	public void humanTurnEnd(ActorRef out) {
		clear(out);
		humanPlayer.setMana(0);
		BasicCommands.setPlayer1Mana(out, humanPlayer);			
		humanPlayer.drawCard(out, this, 1, 0);
	}

	public void aiTurnStart(ActorRef out) {
		BasicCommands.addPlayer1Notification(out, "ai's Round", 2);
		player = aiPlayer;
		aiPlayer.setMana(Round + 1);
		BasicCommands.setPlayer2Mana(out, aiPlayer);
		for (Unit u:aiPlayer.summoned) {
			u.enableMoveAttack();
		}
		//try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
	}
	
	public void aiTurnEnd(ActorRef out) {
		aiPlayer.setMana(0);
		BasicCommands.setPlayer2Mana(out, aiPlayer);
		aiPlayer.drawCard(out, this, 1, 0);
	}
	
	public void gameEnd(ActorRef out) {
		if (humanPlayer.getHealth()<=0) {
			clear(out);
			BasicCommands.addPlayer1Notification(out, "ai Wins!", 2);
		} else {
			clear(out);
			BasicCommands.addPlayer1Notification(out, "You Win!", 2);
		}
	}
	

	
	
	

}
