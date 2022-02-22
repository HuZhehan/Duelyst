package events;

public enum PreviousEvent {
	//spellCardClicked,
	//unitCardClicked,
	unitClicked, // player has clicked a friend unit
	cardClicked, // player has clicked a card in hand
	//endTurnClicked, 
	block, // unit is moving or ai's is taking turn, player are not allowed to do any click event
	
}
