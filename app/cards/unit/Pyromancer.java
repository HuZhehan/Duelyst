package cards.unit;

import cards.UnitCard;
import structures.basic.*;

/** 
 * UnitCard class of Pyromancer
 * @author Student. Zhehan Hu
 */
public class Pyromancer extends UnitCard{
	
	public Pyromancer() {
		super();
		manacost = 2;
		// attack = 2;
		// health = 1;
		// ownername = "aiPlayer";
	}
	
	public Pyromancer(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
}
