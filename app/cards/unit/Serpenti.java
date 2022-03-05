package cards.unit;

import cards.UnitCard;
import structures.basic.*;

/** 
 * UnitCard class of Serpenti
 * @author Student. Zhehan Hu
 */
public class Serpenti extends UnitCard{
	
	public Serpenti() {
		super();
		manacost = 6;
		// attack = 7;
		// health = 4;
		// ownername = "aiPlayer";
	}
	
	public Serpenti(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
}
