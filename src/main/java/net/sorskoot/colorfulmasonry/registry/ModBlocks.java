package net.sorskoot.colorfulmasonry.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sorskoot.colorfulmasonry.ColorfulMasonry;
import net.sorskoot.colorfulmasonry.blocks.DyedBrickBlock;
import net.sorskoot.colorfulmasonry.blocks.DyedBrickSlabBlock;
import net.sorskoot.colorfulmasonry.blocks.DyedBrickStairsBlock;
import net.sorskoot.colorfulmasonry.blocks.DyedBrickWallBlock;

public class ModBlocks {    
    public static void registerBlocks() {        

        for (DyeColor dyeColor : DyeColor.values()) {            
            RegisterHelper.registerBuildingBlock(new DyedBrickBlock(), dyeColor+"_bricks");
            RegisterHelper.registerBuildingBlock(new DyedBrickSlabBlock(), dyeColor+"_brick_slabs");
            RegisterHelper.registerBuildingBlock(new DyedBrickStairsBlock(Blocks.BRICK_STAIRS.getDefaultState(), FabricBlockSettings.copy(Blocks.BRICK_STAIRS)), dyeColor+"_brick_stairs");
            RegisterHelper.registerBuildingBlock(new DyedBrickWallBlock(), dyeColor+"_brick_wall");
        }
    }


}
