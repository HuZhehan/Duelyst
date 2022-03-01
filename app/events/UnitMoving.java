package events;

import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import structures.GameState;

/**
 * Indicates that a unit instance has started a move. 
 * The event reports the unique id of the unit.
 * 
 * { 
 *   messageType = “unitMoving”
 *   id = <unit id>
 * }
 * 
 * @author Dr. Richard McCreadie
 * @author Student. Zhehan Hu
 *
 */
public class UnitMoving implements EventProcessor{

	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {
		// when unit is moving, block palyer's click
		int unitid = message.get("id").asInt();
		gameState.previousEvent = PreviousEvent.block;
		
	}

}
