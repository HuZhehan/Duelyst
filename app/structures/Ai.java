package structures;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.basic.*;
import utils.BasicObjectBuilders;

public class Ai {

	public static void useUnitCard(ActorRef out, GameState gameState) {
		for (int a=0;a<gameState.aiPlayer.hand.size()-1;a++) {
			// step1: list all valid tile
			Card c = gameState.aiPlayer.hand.get(a);
			if (c.getType()=="UnitCard") {
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
				if (validTile.size()>0) {
					int x = gameState.aiPlayer.summoned.get(0).getPosition().getTilex();
					int y = gameState.aiPlayer.summoned.get(0).getPosition().getTiley();
					Tile best = validTile.get(0);
					int smallest = 100;
					for (int b=0;b<validTile.size()-1;b++) {
						int m = validTile.get(b).getTilex();
						int n = validTile.get(b).getTiley();
						int distance = (int) (Math.pow(x-m, 2)+Math.pow(y-n, 2));
						if (distance<smallest&&m<=x){
							smallest = distance;
							best = validTile.get(b);
						}
					}
					// step3: summon tile on chosen tile
					BasicCommands.addPlayer1Notification(out, "Ai summoned "+c.getCardname(), 2);
					gameState.aiPlayer.useCard(out, gameState, c.getId(), best);
					try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		}
	}

	public static void useSpellCard(ActorRef out, GameState gameState) {
		for (int a=0;a<gameState.aiPlayer.hand.size()-1;a++) {
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
				if (validTile.size()>0) {
					Tile best = validTile.get(0);
					int largest = 0;
					for (int b=0;b<validTile.size()-1;b++) {
						int hp = validTile.get(b).getUnit().getHealth();
						if (hp>largest){
							largest = hp;
							best = validTile.get(b);
						}
					}
					// step3: use spell card on chosen tile
					BasicCommands.addPlayer1Notification(out, "Ai used "+c.getCardname(), 2);
					gameState.aiPlayer.useCard(out, gameState, c.getId(), best);
					try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		}
	}

	public static void unitAction(ActorRef out, GameState gameState) {
		// TODO Auto-generated method stub
		
	}

}
