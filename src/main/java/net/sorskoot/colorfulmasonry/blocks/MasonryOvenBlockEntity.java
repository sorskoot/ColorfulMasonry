package net.sorskoot.colorfulmasonry.blocks;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.sorskoot.colorfulmasonry.ColorfulMasonry;
import net.sorskoot.colorfulmasonry.registry.MasonryOvenBlocks;

public class MasonryOvenBlockEntity extends AbstractFurnaceBlockEntity {

    public MasonryOvenBlockEntity() {
        super(MasonryOvenBlocks.MASONRY_OVEN_BLOCK_ENTITY, 
         MasonryOvenBlocks.MASONRY_OVEN_RECIPE_TYPE);
    }

    @Override
    protected Text getContainerName() {
        return Text.of("Masonry Oven");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return null;
    }
    
}

