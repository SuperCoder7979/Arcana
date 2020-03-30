package net.kineticdevelopment.arcana.common.init;

import net.kineticdevelopment.arcana.common.items.ItemAttachment;
import net.kineticdevelopment.arcana.common.items.attachment.Cap;
import net.kineticdevelopment.arcana.common.items.attachment.Focus;
import net.kineticdevelopment.arcana.common.items.attachment.WandCore;
import net.kineticdevelopment.arcana.common.objects.items.ItemBase;
import net.kineticdevelopment.arcana.common.objects.items.ItemFocus;
import net.kineticdevelopment.arcana.common.objects.items.ItemWand;
import net.kineticdevelopment.arcana.common.objects.items.armor.GoggleBase;
import net.kineticdevelopment.arcana.common.objects.items.tools.*;
import net.kineticdevelopment.arcana.core.Main;
import net.kineticdevelopment.arcana.utilities.GogglePriority;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Initialize Items here
 * @author Merijn, Tea, Wilkon
 * @see BlockStateInit
 * @see EntityInit
 * @see BlockInit
 */
public class ItemInit {

	public static final List<Item> ITEMS = new ArrayList<Item>();

	// Materials - Values
	public static Item.ToolMaterial MATERIAL_ARCANIUM = EnumHelper.addToolMaterial("material_arcanium",4,1987,9.0F,4.0F, 20);
	public static Item.ToolMaterial MATERIAL_VOID_METAL = EnumHelper.addToolMaterial("material_void_metal",5,250, 10.0F, 4.5F,8);
	public static ItemArmor.ArmorMaterial MATERIAL_GOGGLES_REVEALING = EnumHelper.addArmorMaterial("material_goggles", Main.MODID + ":goggles_of_revealing", 50, new int[] {0, 0, 0, 2}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);

	//Weapons
	public static Item ARCANIUM_AXE = new AxeBase("arcanium_axe", MATERIAL_ARCANIUM).setCreativeTab(Main.TAB_ARCANA);
	public static Item ARCANIUM_SWORD = new SwordBase("arcanium_sword", MATERIAL_ARCANIUM).setCreativeTab(Main.TAB_ARCANA);
	public static Item VOID_METAL_AXE = new AxeBase("void_metal_axe", MATERIAL_VOID_METAL).setCreativeTab(Main.TAB_ARCANA);
	public static Item VOID_METAL_SWORD = new SwordBase("void_metal_sword", MATERIAL_VOID_METAL).setCreativeTab(Main.TAB_ARCANA);

	//Tools
	public static Item ARCANIUM_HOE = new HoeBase("arcanium_hoe", MATERIAL_ARCANIUM).setCreativeTab(Main.TAB_ARCANA);
	public static Item ARCANIUM_PICKAXE = new PickaxeBase("arcanium_pickaxe", MATERIAL_ARCANIUM).setCreativeTab(Main.TAB_ARCANA);
	public static Item ARCANIUM_SHOVEL = new ShovelBase("arcanium_shovel", MATERIAL_ARCANIUM).setCreativeTab(Main.TAB_ARCANA);
	public static Item VOID_METAL_HOE = new HoeBase("void_metal_hoe", MATERIAL_VOID_METAL).setCreativeTab(Main.TAB_ARCANA);
	public static Item VOID_METAL_PICKAXE = new PickaxeBase("void_metal_pickaxe", MATERIAL_VOID_METAL).setCreativeTab(Main.TAB_ARCANA);
	public static Item VOID_METAL_SHOVEL = new ShovelBase("void_metal_shovel", MATERIAL_VOID_METAL).setCreativeTab(Main.TAB_ARCANA);

	//Items With Function

	public static Item RESEARCH_NOTE_COMPLETE = new ItemBase("research_note_complete").setCreativeTab(Main.TAB_ARCANA);
	public static Item RESEARCH_NOTE = new ItemBase("research_note").setCreativeTab(Main.TAB_ARCANA);

	//Food - Will add food class soon --Tea :D
	public static Item CAT_MEAT_COOKED = new ItemBase("cooked_cat_meat").setCreativeTab(Main.TAB_ARCANA);
	public static Item CAT_MEAT_UNCOOKED = new ItemBase("cat_meat").setCreativeTab(Main.TAB_ARCANA);
	public static Item DOG_MEAT_COOKED = new ItemBase("cooked_dog_meat").setCreativeTab(Main.TAB_ARCANA);
	public static Item DOG_MEAT_UNCOOKED = new ItemBase("dog_meat").setCreativeTab(Main.TAB_ARCANA);

	// Ingots
	public static Item THAUMIUM_INGOT = new ItemBase("thaumium_ingot").setCreativeTab(Main.TAB_ARCANA);
	public static Item ARCANIUM_INGOT = new ItemBase("arcanium_ingot").setCreativeTab(Main.TAB_ARCANA);
	public static Item VOID_METAL_INGOT = new ItemBase("void_metal_ingot").setCreativeTab(Main.TAB_ARCANA);

	// Materials - Items
	public static Item SILVERWOOD_STICK = new ItemBase("silverwood_stick").setCreativeTab(Main.TAB_ARCANA);

	// Armor/Goggles
	public static GoggleBase GOGGLES_OF_REVEALING = new GoggleBase("goggles_of_revealing", MATERIAL_GOGGLES_REVEALING, 1, GogglePriority.SHOW_NODE);

	// Wand Parts
	public static Item FOCUS_PARTS = new ItemBase("focus_parts").setCreativeTab(Main.TAB_ARCANA);

	// Wand Attachments
	public static Cap IRON_CAP = (Cap)new Cap("iron_cap").setId(0).setCreativeTab(Main.TAB_ARCANA);
	public static Cap GOLD_CAP = (Cap)new Cap("gold_cap").setId(1).setCreativeTab(Main.TAB_ARCANA);
	public static Cap THAUMIUM_CAP = (Cap)new Cap("thaumium_cap").setId(2).setCreativeTab(Main.TAB_ARCANA);
	public static Cap VOID_CAP = (Cap)new Cap("void_cap").setId(3).setCreativeTab(Main.TAB_ARCANA);
	public static Cap COPPER_CAP = (Cap)new Cap("copper_cap").setId(4).setCreativeTab(Main.TAB_ARCANA);
	public static Cap SILVER_CAP = (Cap)new Cap("silver_cap").setId(5).setCreativeTab(Main.TAB_ARCANA);
	public static Cap ELEMENTIUM_CAP = (Cap)new Cap("elementium_cap").setId(6).setCreativeTab(Main.TAB_ARCANA);
	public static Cap MANASTEEL_CAP = (Cap)new Cap("manasteel_cap").setId(7).setCreativeTab(Main.TAB_ARCANA);
	public static Cap TERRASTEEL_CAP = (Cap)new Cap("terrasteel_cap").setId(8).setCreativeTab(Main.TAB_ARCANA);

	public static ItemAttachment[] CAPS = new ItemAttachment[]{IRON_CAP, GOLD_CAP, THAUMIUM_CAP, VOID_CAP, COPPER_CAP, SILVER_CAP, ELEMENTIUM_CAP, MANASTEEL_CAP, TERRASTEEL_CAP};

	public static Item FOCUS = new ItemFocus("focus");

	// Wand stuff
	public static ItemWand WOOD_WAND = (ItemWand)new ItemWand("wood_wand").setAllowedCaps(IRON_CAP)
	/*.setAttachments(() -> {
		ItemAttachment[][] attachments = new ItemAttachment[2][CAPS.length];
		attachments[0] = new ItemAttachment[] {IRON_CAP};
		attachments[1] = new ItemAttachment[] {Focus.DEFAULT};
		return attachments;
	})*/.setCreativeTab(Main.TAB_ARCANA);

	public static ItemWand GREATWOOD_WAND = (ItemWand)new ItemWand("greatwood_wand")/*.setAttachments(() -> {
		ItemAttachment[][] attachments = new ItemAttachment[2][CAPS.length];

		attachments[0] = new ItemAttachment[] {IRON_CAP, COPPER_CAP, GOLD_CAP, SILVER_CAP, MANASTEEL_CAP};

		attachments[1] = new ItemAttachment[] {Focus.DEFAULT};
		return attachments;
	})*/.setCreativeTab(Main.TAB_ARCANA);

	public static ItemWand TAINTED_WAND = (ItemWand)new ItemWand("tainted_wand")/*.setAttachments(() -> {
		ItemAttachment[][] attachments = new ItemAttachment[2][CAPS.length];

		attachments[0] = new ItemAttachment[] {IRON_CAP, COPPER_CAP, GOLD_CAP, SILVER_CAP, MANASTEEL_CAP};

		attachments[1] = new ItemAttachment[] {Focus.DEFAULT};
		return attachments;
	})*/.setCreativeTab(Main.TAB_ARCANA);

	public static ItemWand DAIR_WAND = (ItemWand)new ItemWand("dair_wand").setDisallowedCaps(TERRASTEEL_CAP).setCreativeTab(Main.TAB_ARCANA);
	public static ItemWand HAWTHORN_WAND = (ItemWand)new ItemWand("hawthorn_wand").setDisallowedCaps(TERRASTEEL_CAP).setCreativeTab(Main.TAB_ARCANA);
	public static ItemWand SILVERWOOD_WAND = (ItemWand)new ItemWand("silverwood_wand").setCreativeTab(Main.TAB_ARCANA);
	public static ItemWand ARCANIUM_WAND = (ItemWand)new ItemWand("arcanium_wand").setCreativeTab(Main.TAB_ARCANA);

	// iLlEgAl fOrWaRd rEfErEnCe I don't care

	public static Item GREATWOOD_WAND_CORE = new WandCore("greatwood_wand_core", GREATWOOD_WAND).setCreativeTab(Main.TAB_ARCANA);
	public static Item TAINTED_WAND_CORE = new WandCore("tainted_wand_core", TAINTED_WAND).setCreativeTab(Main.TAB_ARCANA);
	public static Item DAIR_WAND_CORE = new WandCore("dair_wand_core", DAIR_WAND).setCreativeTab(Main.TAB_ARCANA);
	public static Item HAWTHORN_WAND_CORE = new WandCore("hawthorn_wand_core", HAWTHORN_WAND).setCreativeTab(Main.TAB_ARCANA);
	public static Item SILVERWOOD_WAND_CORE = new WandCore("silverwood_wand_core", SILVERWOOD_WAND).setCreativeTab(Main.TAB_ARCANA);
	public static Item ARCANIUM_WAND_CORE = new WandCore("arcanium_wand_core", ARCANIUM_WAND).setCreativeTab(Main.TAB_ARCANA);
	// Wooden wands are supported by RecipeWands.
}