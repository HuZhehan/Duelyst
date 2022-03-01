package cards.unit;

import akka.actor.ActorRef;
import cards.UnitCard;
import commands.BasicCommands;
import structures.GameState;
import structures.basic.*;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

/** 
 * UnitCard class of Azure_Herald
 * @author Student. Zhehan Hu
 */
public class Azure_Herald extends UnitCard{
	
	public Azure_Herald() {
		super();
		manacost = 2;
		// attack = 1;
		// health = 4;
		// ownername = "HumanPlayer";
	}
	
	public Azure_Herald(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
	
	// Apply ability: When this unit is summoned give your avatar +3 health (maximum 20)
	public void content(ActorRef out, GameState gameState, Tile tile) {
		gameState.player.summon(out, gameState, id, tile);
		//BasicCommands.addPlayer1Notification(out, "Trigger: On-Summon", 2);
		for (int i=0;i<9;i++) {
			for (int j=0;j<5;j++) {
				if (gameState.tile[i][j].getUnit()!=null) {
					Unit u = gameState.tile[i][j].getUnit();
					// must be your avatar
					if ((u.getId()==100 || u.getId()==200)&&u.getOwner()==this.getOwner())  {
						BasicCommands.playEffectAnimation(out, BasicObjectBuilders.loadEffect(StaticConfFiles.f1_buff), gameState.tile[i][j]);
						// avatar get +3 health
						u.takeHeal(out, gameState, 3);
						// try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
						break;
					}
				}
			}
		}
	}
}
