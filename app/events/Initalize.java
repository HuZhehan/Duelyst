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
import units.Avatar;
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
		gameState.humanPlayer.setHealth(out, 20);
		Unit humanAvatar = gameState.humanPlayer.createUnit(out, StaticConfFiles.humanAvatar, 0, gameState.tile[1][2], Avatar.class);

		// set enemy
		gameState.aiPlayer.setHealth(out, 20);
		Unit aiAvatar = gameState.aiPlayer.createUnit(out, StaticConfFiles.aiAvatar, 20, gameState.tile[7][2], Avatar.class);
		
		
		
		// draw card
		gameState.humanPlayer.drawCard(out,3,0);
		gameState.aiPlayer.drawCard(out,3,-1);
				
		//test	
		humanAvatar.move(out, gameState.tile[6][2]);
		humanAvatar.attack(out, aiAvatar);
		
		gameState.gameInitalised = true;
		gameState.something = true;
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		BasicCommands.addPlayer1Notification(out, "initalization done", 20);
		
		
		//CommandDemo.executeDemo(out); // this executes the command demo, comment out this when implementing your solution
		//CheckMoveLogic.executeDemo(out);
	}
	

}


