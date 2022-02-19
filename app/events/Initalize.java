package events;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import commands.BasicCommands;
import demo.CheckMoveLogic;
import demo.CommandDemo;
import structures.GameState;
import structures.basic.BetterUnit;
import structures.basic.Card;
import structures.basic.EffectAnimation;
import structures.basic.Player;
import structures.basic.Tile;
import structures.basic.Unit;
import structures.basic.UnitAnimationType;
import units.HumanAvatar;
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
		
		// 
		gameState.createBoard(out);
		
		// set player
		Unit humanAvatar = gameState.humanPlayer.summon(out, gameState, 100, gameState.tile[1][2]);

		// set enemy
		Unit aiAvatar = gameState.aiPlayer.summon(out, gameState, 200, gameState.tile[7][2]);
		
		
		// draw card
		gameState.humanPlayer.drawCard(out, gameState, 3, 0);
		gameState.aiPlayer.drawCard(out, gameState, 3, -1);
				
		//test	
		gameState.tile[1][2].setUnit(humanAvatar);
		humanAvatar.move(out, gameState, gameState.tile[6][2]);
		humanAvatar.attack(out, gameState, aiAvatar);
		
		gameState.gameInitalised = true;
		gameState.something = true;
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		BasicCommands.addPlayer1Notification(out, "initalization done", 20);
		
		
		//CommandDemo.executeDemo(out); // this executes the command demo, comment out this when implementing your solution
		//CheckMoveLogic.executeDemo(out);
	}
	

}


