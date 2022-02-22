package events;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import cards.*;
import commands.BasicCommands;
import demo.CheckMoveLogic;
import demo.CommandDemo;
import structures.GameState;
import structures.basic.*;
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
		BasicCommands.addPlayer1Notification(out, "initalization", 2);
		
		// 
		gameState.createBoard(out);
		
		// set player
		Unit humanAvatar = gameState.humanPlayer.summon(out, gameState, 100, gameState.tile[6][2]);
		BasicCommands.setPlayer1Mana(out, gameState.humanPlayer);
		BasicCommands.setPlayer1Health(out, gameState.humanPlayer);


		// set enemy
		Unit aiAvatar = gameState.aiPlayer.summon(out, gameState, 200, gameState.tile[7][2]);
		BasicCommands.setPlayer2Mana(out, gameState.aiPlayer);
		BasicCommands.setPlayer2Health(out, gameState.aiPlayer);
		
		
		// draw card
		gameState.humanPlayer.drawCard(out, gameState, 3, 0);
		gameState.aiPlayer.drawCard(out, gameState, 3, -1);
				
		//test	
		//humanAvatar.move(out, gameState, gameState.tile[5][4]);
		//humanAvatar.attack(out, gameState, aiAvatar);
		//Card card1 = BasicObjectBuilders.loadCard(StaticConfFiles.c_truestrike, 14, Truestrike.class);
		//card1.act(out,gameState, gameState.tile[7][2]);
		//Card card2 = BasicObjectBuilders.loadCard(StaticConfFiles.c_comodo_charger, 0, ComboCharger.class);
		//card2.act(out,gameState, gameState.tile[7][1]);
		
		
		//gameState.something = true;
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		gameState.gameInitalised = true;
		
		// update
		gameState.Round++;

		//player start new turn
		BasicCommands.addPlayer1Notification(out, "Round"+Integer.toString(gameState.Round), 2);
		gameState.humanPlayer.setMana(gameState.Round + 1);
		BasicCommands.setPlayer1Mana(out, gameState.humanPlayer);
		
		//CommandDemo.executeDemo(out); // this executes the command demo, comment out this when implementing your solution
		//CheckMoveLogic.executeDemo(out);
	}
	

}


