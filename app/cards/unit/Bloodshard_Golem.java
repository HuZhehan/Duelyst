package cards.unit;

import cards.UnitCard;
import structures.basic.*;

/** 
 * UnitCard class of Bloodshard_Golem
 * @author Student. Zhehan Hu
 */
public class Bloodshard_Golem extends UnitCard{
	
	public Bloodshard_Golem() {
		super();
		manacost = 3;
		// attack = 4;
		// health = 3;
		// ownername = "aiPlayer";
	}
	
	public Bloodshard_Golem(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
}

