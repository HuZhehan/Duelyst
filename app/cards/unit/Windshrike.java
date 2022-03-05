package cards.unit;

import cards.UnitCard;
import structures.basic.*;

/** 
 * UnitCard class of Windshrike
 * @author Student. Zhehan Hu
 */
public class Windshrike extends UnitCard{
	
	public Windshrike() {
		super();
		manacost = 4;
		// attack = 4;
		// health = 3;
		// ownername = "aiPlayer";
	}
	
	public Windshrike(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
}

