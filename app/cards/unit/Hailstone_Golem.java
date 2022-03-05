package cards.unit;

import cards.UnitCard;
import structures.basic.*;

/** 
 * UnitCard class of Hailstone_Golem
 * @author Student. Zhehan Hu
 */
public class Hailstone_Golem extends UnitCard{
	
	public Hailstone_Golem() {
		super();
		manacost = 4;
		// attack = 4;
		// health = 6;
		// ownername = "HumanPlayer";
		// ownername = "AiPlayer";
	}
	
	public Hailstone_Golem(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
}
