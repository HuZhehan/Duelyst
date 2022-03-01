package events;
/**
 * This is an enumerate that simply holds the names
 * of the various previous click event.
 * 
 * @author Student. Zhehan Hu
 *
 */
public enum PreviousEvent {
	
	unitClicked, // player has clicked a friend unit
	cardClicked, // player has clicked a card in hand
	block, // unit is moving or ai's is taking turn, player are not allowed to do any click event
	//endTurnClicked, // not used
	//spellCardClicked, // not used
	//unitCardClicked, // not used
}
