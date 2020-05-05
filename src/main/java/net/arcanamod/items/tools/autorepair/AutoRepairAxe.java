package net.arcanamod.items.tools.autorepair;

import net.arcanamod.items.tools.AxeBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

/**
 * @author Wilkon, Mozaran (With assistance from HAVOC)
 * <p>
 * Meant for the Void Metal Axe but can be used for other auto repair axe.
 */

public class AutoRepairAxe extends AxeBase{
	private final int FULL_TIMER = 100;
	
	public AutoRepairAxe(String name, ToolMaterial material){
		super(name, material);
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged){
		return slotChanged;
	}
	
	@Override
	public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack){
		return !(newStack.getItem() == oldStack.getItem() && (newStack.isItemStackDamageable() || newStack.getMetadata() == oldStack.getMetadata()));
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected){
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		if(isSelected){
			CompoundNBT tag = stack.getTagCompound();
			if(tag == null){
				stack.setTagCompound(new CompoundNBT());
				tag = stack.getTagCompound();
			}
			if(tag != null && !tag.hasKey("repair_timer")){
				tag.setInteger("repair_timer", FULL_TIMER);
			}
			
			if(tag != null && tag.getInteger("repair_timer") > 0){
				tag.setInteger("repair_timer", tag.getInteger("repair_timer") - 1);
			}else if(tag != null){
				tag.setInteger("repair_timer", FULL_TIMER);
				stack.damageItem(-1, (LivingEntity)entityIn);
			}
		}
	}
}