package utils;

import java.util.ArrayList;
import java.util.List;

import structures.basic.Unit;
import units.*;

/**
 * @author Zhehan Hu, 
 */

public class UnitLoader {
	
	public static List<Unit> getPlayer1Units() {
		
		List<Unit> army = new ArrayList<Unit>();
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.humanAvatar, 100, HumanAvatar.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_comodo_charger, 0, ComboCharger.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_pureblade_enforcer, 1, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_fire_spitter, 2, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_silverguard_knight, 3, Unit.class));
		//units.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_truestrike, 4, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_azure_herald, 5, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_ironcliff_guardian, 6, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_azurite_lion, 7, Unit.class));
		//units.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_sundrop_elixir, 8, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_hailstone_golem, 9, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_silverguard_knight, 10, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_fire_spitter, 11, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_comodo_charger, 12, ComboCharger.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_pureblade_enforcer, 13, Unit.class));
		//units.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_truestrike, 14, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_azure_herald, 15, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_ironcliff_guardian, 16, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_azurite_lion, 17, Unit.class));
		//units.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_sundrop_elixir, 18, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_hailstone_golem, 19, Unit.class));
		
		return army;
		}
	
	public static List<Unit> getPlayer2Units() {
		
		List<Unit> army = new ArrayList<Unit>();
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.aiAvatar, 200, AiAvatar.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_rock_pulveriser, 20, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_bloodshard_golem, 21, Unit.class));
		//unitlist.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_staff_of_ykir, 22, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_blaze_hound, 23, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_windshrike, 24, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_pyromancer, 25, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_serpenti, 26, Unit.class));
		//unitlist.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_entropic_decay, 27, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_planar_scout, 28, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_hailstone_golem, 29, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_rock_pulveriser, 30, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_bloodshard_golem, 31, Unit.class));
		//unitlist.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_staff_of_ykir, 32, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_blaze_hound, 33, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_windshrike, 34, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_pyromancer, 35, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_serpenti, 36, Unit.class));
		//unitlist.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_entropic_decay, 37, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_planar_scout, 38, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_hailstone_golem, 39, Unit.class));
	
		return army;
	}

}
