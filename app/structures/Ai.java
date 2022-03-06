package structures;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.basic.*;

/**
 * This class contains methods that AI player can call to play auto action.
 * @author Student. Zhehan Hu
 */
public class Ai {
	
	/**
	 * AI player look through unit cards in hand and try to use.
	 * For each card,
	 * STEP 1: list all valid tile;
	 * STEP 2: choose a best tile;
	 * STEP 3: summon unit on chosen tile.
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 */
	public static void useUnitCard(ActorRef out, GameState gameState) {
		out:
		while(gameState.gameOver == false) {
			for (int a=0;a<gameState.aiPlayer.hand.size();a++) {
				// step1: list all valid tile
				Card c = gameState.aiPlayer.hand.get(a);
				if (c.getType()=="UnitCard") {
					List<Tile> validTile = new ArrayList<Tile>();
					for (int i=0;i<9;i++) {
						for (int j=5-1;j>=0;j--) {
							Tile tile = gameState.tile[i][j];
							if (c.check(out, gameState, tile)==true) {
								validTile.add(tile);
							};
						}
					}
					// step2: choose a best tile
					// the nearest to Avatar
					// not at the back of Avatar
					if (validTile.size()>0) {
						int x = gameState.aiPlayer.summoned.get(0).getPosition().getTilex();
						int y = gameState.aiPlayer.summoned.get(0).getPosition().getTiley();
						Tile best = validTile.get(0);
						int smallest = 1000;
						for (int b=0;b<validTile.size();b++) {
							int m = validTile.get(b).getTilex();
							int n = validTile.get(b).getTiley();
							int distance = (int) (Math.pow(x-m, 2)+Math.pow(2-n, 2));
							if (distance<smallest&&m<=x){
								smallest = distance;
								best = validTile.get(b);
							}
						}
						// step3: summon unit on chosen tile
						BasicCommands.addPlayer1Notification(out, "Ai summoned "+c.getCardname(), 2);
						gameState.aiPlayer.useCard(out, gameState, c.getId(), best);
						try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
						if (gameState.gameOver == true){break out;}
					}
				}
			}
			break;
		}
	}

	/**
	 * AI player look through spell cards in hand and try to use.
	 * For each card,
	 * STEP 1: list all valid tile;
	 * STEP 2: choose a best tile;
	 * STEP 3: apply affect on chosen tile.
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 */
	public static void useSpellCard(ActorRef out, GameState gameState) {
		out:
		while(gameState.gameOver == false) {
			for (int a=0;a<gameState.aiPlayer.hand.size();a++) {
				// step1: list all valid tile
				Card c = gameState.aiPlayer.hand.get(a);
				if (c.getType()=="SpellCard") {
					List<Tile> validTile = new ArrayList<Tile>();
					for (int i=0;i<9;i++) {
						for (int j=0;j<5;j++) {
							Tile tile = gameState.tile[i][j];
							if (c.check(out, gameState, tile)==true) {
								validTile.add(tile);
							};
						}
					}
					// step2: choose a best tile
					// for Staff of Y’Kir, only Avatar's tile is valid
					// for Entropic Decay, choose the unit with highest hp
					if (validTile.size()>0) {
						Tile best = validTile.get(0);
						int highest = 0;
						for (int b=0;b<validTile.size();b++) {
							int hp = validTile.get(b).getUnit().getHealth();
							if (hp>highest){
								highest = hp;
								best = validTile.get(b);
							}
						}
						// step3: use spell card on chosen tile
						BasicCommands.addPlayer1Notification(out, "Ai used "+c.getCardname(), 2);
						gameState.aiPlayer.useCard(out, gameState, c.getId(), best);
						try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
						if (gameState.gameOver == true){break out;}
					}
				}
			}
			break;
		}
	}

	/**
	 * AI player look through units summoned and try to play action.
	 * For each unit,
	 * STEP 1: list all valid tile;
	 * STEP 2: choose a best tile;
	 * STEP 3: unit acts on chosen tile.
	 * @author Student. Zhehan Hu
	 * @param out
	 * @param gameState
	 */
	public static void unitAction(ActorRef out, GameState gameState) {
		out:
		while(gameState.gameOver == false) {
			for (int a=0;a<gameState.aiPlayer.summoned.size();a++) {
				// step1: list all valid tile
				Unit u = gameState.aiPlayer.summoned.get(a);
				if (u.getDead()==false) {
					List<Tile> validAttackTile = new ArrayList<Tile>();
					List<Tile> validMoveTile = new ArrayList<Tile>();
					for (int i=0;i<9;i++) {
						for (int j=0;j<5;j++) {
							Tile tile = gameState.tile[i][j];
							if (u.check(out, gameState, tile)==true && tile.getUnit()!=null) {
								validAttackTile.add(tile);
							}
							else if (u.check(out, gameState, tile)==true && tile.getUnit()==null) {
								validMoveTile.add(tile);
							}
						}
					}
					// step2: choose a best tile
					// action priority: attack > move
					if (validAttackTile.size()>0 || validMoveTile.size()>0) {
						Tile best;
						// attack target priority: human avatar > normal unit, low hp > high hp
						if (validAttackTile.size()>0) {
							best = validAttackTile.get(0);
							int lowest = 1000;
							for (int b=0;b<validAttackTile.size();b++) {
								int hp = validAttackTile.get(b).getUnit().getHealth();
								if (hp<lowest || (validAttackTile.get(b).getUnit()!=null&&validAttackTile.get(b).getUnit().getId()==100)){
									lowest = Math.min(hp,lowest);
									best = validAttackTile.get(b);
								}
							}
						} 
						// move to tile priority: close to human avatar > far from human avatar
						// best tile is valid tile nearest to human avatar
						else {
							best = validMoveTile.get(0);
							int m = gameState.humanPlayer.summoned.get(0).getPosition().getTilex();
							int n = gameState.humanPlayer.summoned.get(0).getPosition().getTiley();
							int lowest = 1000;
							for (int b=0;b<validMoveTile.size();b++) {
								int x = u.getPosition().getTilex();
								int y = u.getPosition().getTiley();
								int distance = Math.abs(m-x)+Math.abs(n-y);
								if (distance<lowest){
									lowest = distance;
									best = validMoveTile.get(b);
								}
							}
						}
						// step3: unit acts on chosen tile
						BasicCommands.addPlayer1Notification(out, "Ai' "+u.getName()+" acted", 2);
						if (best.getUnit()==null) {
							// move
							u.move(out, gameState, best);
						}
						// enemy unit on tile, attack
						else if (best.getUnit()!=null) {
							// attack
							if (u.checkAttack(out, gameState, best) == true){
								u.attack(out, gameState, best.getUnit());
								if (gameState.gameOver == true){break out;}
							}
							// move-attack
							else if (u.checkMoveAttack(out, gameState, best) == true){	
								u.moveAttack(out, gameState, best.getUnit());
								if (gameState.gameOver == true){break out;}
							}
							else {
								BasicCommands.addPlayer1Notification(out, "unitAction failure", 2);
							}
						}
						try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
					}
				}
			}
			break;
		}
	}
}
