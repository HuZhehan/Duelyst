package cards.unit;

import cards.UnitCard;
import structures.basic.*;

/** 
 * UnitCard class of Azurite_Lion
 * @author Student. Zhehan Hu
 */
public class Azurite_Lion extends UnitCard{
	
	public Azurite_Lion() {
		super();
		manacost = 3;
		// attack = 2;
		// health = 3;
		// ownername = "HumanPlayer";
	}
	
	public Azurite_Lion(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id, cardname, manacost, miniCard, bigCard);
	}
}
