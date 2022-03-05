package utils;

import java.util.ArrayList;
import java.util.List;
import cards.spell.*;
import cards.unit.*;
import structures.basic.Card;

/**
 * This is a utility class that provides methods for loading the decks for each
 * player, as the deck ordering is fixed. 
 * @author Richard
 *
 */
public class OrderedCardLoader {

	/**
	 * Returns all of the cards in the human player's deck in order
	 * @return
	 */
	public static List<Card> getPlayer1Cards() {
	
		List<Card> cardsInDeck = new ArrayList<Card>(20);

		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_comodo_charger, 0, Combo_Charger.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_pureblade_enforcer, 1, Pureblade_Enforcer.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_fire_spitter, 2, Fire_Spitter.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_silverguard_knight, 3, Silverguard_Knight.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_truestrike, 4, Truestrike.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_azure_herald, 5, Azure_Herald.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_ironcliff_guardian, 6, Ironcliff_Guardian.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_azurite_lion, 7, Azurite_Lion.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_sundrop_elixir, 8, Sundrop_Elixir.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_hailstone_golem, 9, Hailstone_Golem.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_silverguard_knight, 10, Silverguard_Knight.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_fire_spitter, 11, Fire_Spitter.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_comodo_charger, 12, Combo_Charger.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_pureblade_enforcer, 13, Pureblade_Enforcer.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_truestrike, 14, Truestrike.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_azure_herald, 15, Azure_Herald.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_ironcliff_guardian, 16, Ironcliff_Guardian.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_azurite_lion, 17, Azurite_Lion.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_sundrop_elixir, 18, Sundrop_Elixir.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_hailstone_golem, 19, Hailstone_Golem.class));
	
		
		return cardsInDeck;
	}
	
	
	/**
	 * Returns all of the cards in the human player's deck in order
	 * @return
	 */
	public static List<Card> getPlayer2Cards() {
	
		List<Card> cardsInDeck = new ArrayList<Card>(20);
		
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_rock_pulveriser, 20, Rock_Pulveriser.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_bloodshard_golem, 21, Bloodshard_Golem.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_staff_of_ykir, 22, Staff_of_Ykir.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_blaze_hound, 23, Blaze_Hound.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_windshrike, 24, Windshrike.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_pyromancer, 25, Pyromancer.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_serpenti, 26, Serpenti.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_entropic_decay, 27, Entropic_Decay.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_planar_scout, 28, Planar_Scout.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_hailstone_golem, 29, Hailstone_Golem.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_rock_pulveriser, 30, Rock_Pulveriser.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_bloodshard_golem, 31, Bloodshard_Golem.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_staff_of_ykir, 32, Staff_of_Ykir.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_blaze_hound, 33, Blaze_Hound.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_windshrike, 34, Windshrike.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_pyromancer, 35, Pyromancer.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_serpenti, 36, Serpenti.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_entropic_decay, 37, Entropic_Decay.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_planar_scout, 38, Planar_Scout.class));
		cardsInDeck.add(BasicObjectBuilders.loadCard(StaticConfFiles.c_hailstone_golem, 39, Hailstone_Golem.class));
		
		return cardsInDeck;
	}
	
}
