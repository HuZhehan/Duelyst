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
public class Azure_Herald extends UnitCard{
	
	public Azure_Herald() {
		super();
		manacost = 2;
		attack = 1;
		health = 4;
		ownername = "HumanPlayer";
	}
	
	public Azure_Herald(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
	// When this unit is summoned give your avatar +3 health (maximum 20)
	public void content(ActorRef out, GameState gameState, Tile tile) {
		gameState.player.summon(out, gameState, id, tile);
		BasicCommands.playEffectAnimation(out, BasicObjectBuilders.loadEffect(StaticConfFiles.f1_buff), tile);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		for (Unit u:gameState.humanPlayer.summoned) {
			if (u.getId()==100 || u.getId()==200) {
				u.takeHeal(out, gameState, 3);
				break;
			}
		}
	}
}
