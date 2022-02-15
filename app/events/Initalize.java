package events;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import commands.BasicCommands;
import demo.CheckMoveLogic;
import demo.CommandDemo;
import structures.GameState;
import structures.basic.Card;
import structures.basic.Player;
import structures.basic.Tile;
import structures.basic.Unit;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

/**
 * Indicates that both the core game loop in the browser is starting, meaning
 * that it is ready to recieve commands from the back-end.
 * 
 * { 
 *   messageType = “initalize”
 * }
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class Initalize implements EventProcessor{

	@Override
	// edited by @Zhehan Hu
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {
		// hello this is a change
		BasicCommands.addPlayer1Notification(out, "start initalization", 20);
		
		// tile[column][row], ignore tile[0][0], start from tile[1][1]
		for (int i=1;i<=9;i++) {
			for (int j=1;j<=5;j++) {
					GameState.tile[i][j] = BasicObjectBuilders.loadTile(i-1, j-1);
					BasicCommands.drawTile(out, GameState.tile[i][j], 0);
			}
		}
		
		// set player
		BasicCommands.setPlayer1Health(out, GameState.humanPlayer);
		GameState.humanPlayer.cardsInDeck = utils.OrderedCardLoader.getPlayer1Cards();
		Unit playerAvatar = BasicObjectBuilders.loadUnit(StaticConfFiles.humanAvatar, 0, Unit.class);
		playerAvatar.setPositionByTile(GameState.tile[2][3]); 
		BasicCommands.drawUnit(out, playerAvatar, GameState.tile[2][3]);
		

		// set enemy
		BasicCommands.setPlayer2Health(out, GameState.aiPlayer);
		GameState.aiPlayer.cardsInDeck = utils.OrderedCardLoader.getPlayer1Cards();
		Unit enemyAvatar = BasicObjectBuilders.loadUnit(StaticConfFiles.aiAvatar, 1, Unit.class);
		enemyAvatar.setPositionByTile(GameState.tile[8][3]); 
		BasicCommands.drawUnit(out, enemyAvatar, GameState.tile[8][3]);
			
		// player draw card
		BasicCommands.drawCard(out, GameState.humanPlayer.cardsInDeck.get(0), 1, 0);
		BasicCommands.drawCard(out, GameState.humanPlayer.cardsInDeck.get(1), 2, 0);
		BasicCommands.drawCard(out, GameState.humanPlayer.cardsInDeck.get(2), 3, 0);
		
		gameState.gameInitalised = true;
		gameState.something = true;
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		BasicCommands.addPlayer1Notification(out, "initalization done", 20);
		
		//CommandDemo.executeDemo(out); // this executes the command demo, comment out this when implementing your solution
		//CheckMoveLogic.executeDemo(out);
	}
	

}


