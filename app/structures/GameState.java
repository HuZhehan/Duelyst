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
 * @author Student. Zhehan Hu
 * @author Student. Reetu Varadhan
 *
 */
public class GameState {

	public boolean gameInitalised = false;
	public boolean gameOver = false;
	
	public int Round = 0;
	
	public HumanPlayer humanPlayer = new HumanPlayer();
	public AiPlayer aiPlayer = new AiPlayer();
	public Player player = humanPlayer; // current player
	
	public Tile[][] tile = new Tile[9][5];
	
	public PreviousEvent previousEvent = null;
	public Card previousCard = null;
	public Unit previousUnit = null;
	
	public GameState() {}
	
	/**
	 * Create a 9*5 board and draw it
	 * @author Student. Zhehan Hu
	 * @param out
	 */
	public void createBoard(ActorRef out) {
		for (int i=0;i<9;i++) {
			for (int j=0;j<5;j++) {
				tile[i][j] = BasicObjectBuilders.loadTile(i, j);
					BasicCommands.drawTile(out, tile[i][j], 0);
			}
			try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
	/**
	 * Clear all game states, and
	 * draw board with white tiles, 
	 * draw cards in player's hand without highlight
	 * @author Student. Zhehan Hu
	 * @param out
	 */
	public void clear(ActorRef out) {
		// clear states
		previousEvent = null;
		previousCard = null;
		previousUnit = null;
		// clear board
		clearBoard(out);
		// clear card
		for (Card c : humanPlayer.hand) {
			BasicCommands.drawCard(out, c, humanPlayer.hand.indexOf(c)+1, 0);
		}
		try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
	}
	
	/**
	 * Draw board with white tiles, 
	 * @author Student. Zhehan Hu
	 * @param out
	 */
	public void clearBoard(ActorRef out) {
		// clear board
		for (int i=0;i<9;i++) {
			for (int j=0;j<5;j++) {
				BasicCommands.drawTile(out, tile[i][j], 0);
			}
			try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
	/**
	 * Human player starts his turn,
	 * set mana and reset units' move and attack chance to max.
	 * @author Student. Zhehan Hu
	 * @param out
	 */
	public void humanTurnStart(ActorRef out) {
		BasicCommands.addPlayer1Notification(out, "Your turn "+Integer.toString(Round), 2);
		player = humanPlayer;
		humanPlayer.setMana(Round+1);
		BasicCommands.setPlayer1Mana(out, humanPlayer);
		for (Unit u:humanPlayer.summoned) {
			u.enableMoveAttack();
		}
	}
	
	/**
	 * Human player ends his turn,
	 * reset mana to 0 and draw a card
	 * @author Student. Zhehan Hu
	 * @param out
	 */
	public void humanTurnEnd(ActorRef out) {
		clear(out);
		humanPlayer.setMana(0);
		BasicCommands.setPlayer1Mana(out, humanPlayer);			
		humanPlayer.drawCard(out, this, 1);
	}

	/**
	 * Ai player starts his turn,
	 * set mana and reset units' move and attack chance to max.
	 * @author Student. Zhehan Hu
	 * @param out
	 */
	public void aiTurnStart(ActorRef out) {
		BasicCommands.addPlayer1Notification(out, "Ai's turn "+Integer.toString(Round), 2);
		player = aiPlayer;
		aiPlayer.setMana(Round + 1);
		BasicCommands.setPlayer2Mana(out, aiPlayer);
		for (Unit u:aiPlayer.summoned) {
			u.enableMoveAttack();
		}
		//try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
	}
	
	/**
	 * Ai player uses cards and plays unit actions
	 * @author Student. Zhehan Hu
	 * @param out
	 */
	public void aiTurnUse(ActorRef out) {
		//Ai.unitAction(out, this);
		Ai.useSpellCard(out, this);	
		Ai.useUnitCard(out, this);
		Ai.unitAction(out, this);
	}
	
	/**
	 * Ai player ends his turn,
	 * reset mana to 0 and draw a card.
	 * @author Student. Zhehan Hu
	 * @param out
	 */
	public void aiTurnEnd(ActorRef out) {
		aiPlayer.setMana(0);
		BasicCommands.setPlayer2Mana(out, aiPlayer);
		aiPlayer.drawCard(out, this, 1);
	}
	
	/**
	 * This method checks who win the game and make a notification.
	 * @author Student. Reetu Varadhan
	 * @param out
	 */
	public void gameEnd(ActorRef out) {
		if (humanPlayer.getHealth()<=0||humanPlayer.deck.size()==0) {
			clear(out);
			BasicCommands.addPlayer1Notification(out, "Ai Wins!", 20);
			previousEvent = PreviousEvent.block;
			gameOver = true;
		} 
		else if (aiPlayer.getHealth()<=0||aiPlayer.deck.size()==0){
			clear(out);
			BasicCommands.addPlayer1Notification(out, "You Win!", 20);
			previousEvent = PreviousEvent.block;
			gameOver = true;
		}
		/*
		else {
			BasicCommands.addPlayer1Notification(out, "Fail to get Winner", 20);
		}
		*/
	}
	
}
