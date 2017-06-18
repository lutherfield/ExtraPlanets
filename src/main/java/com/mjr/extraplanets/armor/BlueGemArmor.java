package com.mjr.extraplanets.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import com.mjr.extraplanets.Constants;
import com.mjr.extraplanets.ExtraPlanets;

public class BlueGemArmor extends ItemArmor {
	public String name;

	public BlueGemArmor(String name, ArmorMaterial material, int placement) {
		super(material, 0, placement);
		setCreativeTab(ExtraPlanets.ArmorTab);
		this.name = name;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if (stack.getItem() == ExtraPlanets_Armor.blueGemHelmet || stack.getItem() == ExtraPlanets_Armor.blueGemChest || stack.getItem() == ExtraPlanets_Armor.blueGemBoots) {
			return Constants.TEXTURE_PREFIX + "textures/model/armor/" + name + "_layer_1.png";
		} else if (stack.getItem() == ExtraPlanets_Armor.blueGemLegings) {
			return Constants.TEXTURE_PREFIX + "textures/model/armor/" + name + "_layer_2.png";
		} else {
			return null;
		}
	}
}