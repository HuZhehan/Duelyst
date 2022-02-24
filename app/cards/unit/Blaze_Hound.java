package cards.unit;

import akka.actor.ActorRef;
import cards.UnitCard;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;
/** 
 * @author Zhehan Hu,
 */
public class Blaze_Hound extends UnitCard{
	
	public Blaze_Hound() {
		super();
		manacost = 3;
		// attack = 4;
		// health = 3;
		// ownername = "aiPlayer";
	}
	
	public Blaze_Hound(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
	// When this unit is summoned, both players draw a card
	public void content(ActorRef out, GameState gameState, Tile tile) {
		gameState.player.summon(out, gameState, id, tile);
		gameState.aiPlayer.drawCard(out, gameState, 1);
		gameState.humanPlayer.drawCard(out, gameState, 1);
	}
	
}

