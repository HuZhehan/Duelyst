package cards.unit;

import cards.UnitCard;
import structures.basic.*;

/** 
 * UnitCard class of Rock_Pulveriser
 * @author Student. Zhehan Hu
 */
public class Rock_Pulveriser extends UnitCard{
	
	public Rock_Pulveriser() {
		super();
		manacost = 2;
		// attack = 1;
		// health = 4;
		// ownername = "aiPlayer";
	}
	
	public Rock_Pulveriser(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
}
