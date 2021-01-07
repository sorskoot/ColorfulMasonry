package net.sorskoot.colorfulmasonry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.DyeColor;

public final class DyedBrickBlock extends Block{
	public static final EnumProperty<DyeColor> color = EnumProperty.of("color", DyeColor.class);

	public DyedBrickBlock(DyeColor color) {
        super(FabricBlockSettings.copyOf(Blocks.BRICKS).materialColor(color).nonOpaque());			
	}
}