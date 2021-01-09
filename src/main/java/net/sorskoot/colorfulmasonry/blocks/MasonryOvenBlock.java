package net.sorskoot.colorfulmasonry.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FurnaceBlock;

public class MasonryOvenBlock extends FurnaceBlock {

    public MasonryOvenBlock() {
        super(FabricBlockSettings.copy(Blocks.FURNACE));
    }
    
}
