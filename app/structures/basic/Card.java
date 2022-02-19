package structures.basic;

import akka.actor.ActorRef;
import cards.CardAction;
import structures.GameState;

/**
 * This is the base representation of a Card which is rendered in the player's hand.
 * A card has an id, a name (cardname) and a manacost. A card then has a large and mini
 * version. The mini version is what is rendered at the bottom of the screen. The big
 * version is what is rendered when the player clicks on a card in their hand.
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class Card implements CardAction{
	
	// @author Student Zhehan Hu
	protected String type;
	protected String ownername;
	
	protected int id;
	protected String cardname;
	protected int manacost;
	protected MiniCard miniCard;
	protected BigCard bigCard;
	
	public Card() {};
	
	public Card(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		this();
		this.id = id;
		this.cardname = cardname;
		this.manacost = manacost;
		this.miniCard = miniCard;
		this.bigCard = bigCard;
	}
	public boolean prompt() {
		// TODO Auto-generated method stub
		return false;
	}
	public void content(ActorRef out, GameState gameState, Tile tile) {
		// TODO Auto-generated method stub
	}
	public Player getPlayer(ActorRef out, GameState gameState) {
		if (ownername == "HumanPlayer") {
			return gameState.humanPlayer;
		}
		if (ownername == "AiPlayer") {
			return gameState.aiPlayer;
		}
		return null;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public int getManacost() {
		return manacost;
	}
	public void setManacost(int manacost) {
		this.manacost = manacost;
	}
	public MiniCard getMiniCard() {
		return miniCard;
	}
	public void setMiniCard(MiniCard miniCard) {
		this.miniCard = miniCard;
	}
	public BigCard getBigCard() {
		return bigCard;
	}
	public void setBigCard(BigCard bigCard) {
		this.bigCard = bigCard;
	}

	@Override
	public boolean prompt(ActorRef out, GameState gameState, Tile tile) {
		// TODO Auto-generated method stub
		return false;
	}
}
