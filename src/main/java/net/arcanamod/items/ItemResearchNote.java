package net.arcanamod.items;

import mcp.MethodsReturnNonnullByDefault;
import net.arcanamod.research.Puzzle;
import net.arcanamod.research.ResearchBooks;
import net.arcanamod.research.Researcher;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ItemResearchNote extends ItemBase{
	
	private boolean isComplete;
	
	public ItemResearchNote(String name, boolean complete){
		super(name);
		isComplete = complete;
		setMaxStackSize(1);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand){
		if(!isComplete)
			return super.onItemRightClick(world, player, hand);
		ItemStack stack = player.getHeldItem(hand);
		CompoundNBT compound = stack.getTagCompound();
		if(compound != null && compound.hasKey("puzzle")){
			Researcher from = Researcher.getFrom(player);
			Puzzle puzzle = ResearchBooks.puzzles.get(new ResourceLocation(compound.getString("puzzle")));
			if(!from.isPuzzleCompleted(puzzle)){
				from.completePuzzle(puzzle);
				if(!player.capabilities.isCreativeMode)
					stack.shrink(1);
				return new ActionResult<>(ActionResultType.SUCCESS, stack);
			}
		}
		return super.onItemRightClick(world, player, hand);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag){
		CompoundNBT compound = stack.getTagCompound();
		if(compound != null && compound.hasKey("research")){
			ResourceLocation research = new ResourceLocation(compound.getString("research"));
			tooltip.add(TextFormatting.AQUA + I18n.format(ResearchBooks.getEntry(research).name()));
		}
	}
}