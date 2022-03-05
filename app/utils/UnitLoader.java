package utils;

import java.util.ArrayList;
import java.util.List;
import structures.basic.Unit;
import units.*;

/**
 * @author Student. Zhehan Hu
 */

public class UnitLoader {
	
	public static List<Unit> getPlayer1Units() {
		
		List<Unit> army = new ArrayList<Unit>();
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.humanAvatar, 100, Human_Avatar.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_comodo_charger, 0, ComboCharger.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_pureblade_enforcer, 1, Pureblade_Enforcer.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_fire_spitter, 2, Fire_Spitter.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_silverguard_knight, 3, Silverguard_Knight.class));
		//units.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_truestrike, 4, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_azure_herald, 5, Azure_Herald.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_ironcliff_guardian, 6, Ironcliff_Guardian.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_azurite_lion, 7, Azurite_Lion.class));
		//units.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_sundrop_elixir, 8, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_hailstone_golem, 9, Hailstone_Golem.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_silverguard_knight, 10, Silverguard_Knight.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_fire_spitter, 11, Fire_Spitter.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_comodo_charger, 12, ComboCharger.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_pureblade_enforcer, 13, Pureblade_Enforcer.class));
		//units.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_truestrike, 14, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_azure_herald, 15, Azure_Herald.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_ironcliff_guardian, 16, Ironcliff_Guardian.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_azurite_lion, 17, Azurite_Lion.class));
		//units.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_sundrop_elixir, 18, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_hailstone_golem, 19, Hailstone_Golem.class));
		
		return army;
		}
	
	public static List<Unit> getPlayer2Units() {
		
		List<Unit> army = new ArrayList<Unit>();
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.aiAvatar, 200, Ai_Avatar.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_rock_pulveriser, 20, Rock_Pulveriser.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_bloodshard_golem, 21, Bloodshard_Golem.class));
		//unitlist.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_staff_of_ykir, 22, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_blaze_hound, 23, Blaze_Hound.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_windshrike, 24, Windshrike.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_pyromancer, 25, Pyromancer.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_serpenti, 26, Serpenti.class));
		//unitlist.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_entropic_decay, 27, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_planar_scout, 28, Planar_Scout.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_hailstone_golemR, 29, Hailstone_Golem.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_rock_pulveriser, 30, Rock_Pulveriser.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_bloodshard_golem, 31, Bloodshard_Golem.class));
		//unitlist.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_staff_of_ykir, 32, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_blaze_hound, 33, Blaze_Hound.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_windshrike, 34, Windshrike.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_pyromancer, 35, Pyromancer.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_serpenti, 36, Serpenti.class));
		//unitlist.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_entropic_decay, 37, Unit.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_planar_scout, 38, Planar_Scout.class));
		army.add(BasicObjectBuilders.loadUnit(StaticConfFiles.u_hailstone_golemR, 39, Hailstone_Golem.class));
	
		return army;
	}

}
