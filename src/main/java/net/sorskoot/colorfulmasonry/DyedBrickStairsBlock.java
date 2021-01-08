package net.sorskoot.colorfulmasonry;

import java.util.Collections;
import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;

public final class DyedBrickStairsBlock extends StairsBlock
{
	public DyedBrickStairsBlock(BlockState state, Settings blockSettings)
	{
		super(state, blockSettings);
	}	

	@Override
	public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDroppedStacks(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this, 1));
	}
}