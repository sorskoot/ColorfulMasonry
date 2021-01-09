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
import net.sorskoot.colorfulmasonry.blocks.MasonryOvenBlock;

public class ModBlocks {    
    public static void registerBlocks() {  
        Block oven = new MasonryOvenBlock();
        Registry.register(Registry.BLOCK, new Identifier(ColorfulMasonry.MOD_ID, "masonry_oven"), oven);
        Registry.register(Registry.ITEM, new Identifier(ColorfulMasonry.MOD_ID, "masonry_oven"), new BlockItem(oven, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

       //registerBuildingBlock(new Block(FabricBlockSettings.copy(Blocks.FURNACE)), "MasonryOven");

        for (DyeColor dyeColor : DyeColor.values()) {            
            registerBuildingBlock(new DyedBrickBlock(), dyeColor+"_bricks");
            registerBuildingBlock(new DyedBrickSlabBlock(), dyeColor+"_brick_slabs");
            registerBuildingBlock(new DyedBrickStairsBlock(Blocks.BRICK_STAIRS.getDefaultState(), FabricBlockSettings.copy(Blocks.BRICK_STAIRS)), dyeColor+"_brick_stairs");
            registerBuildingBlock(new DyedBrickWallBlock(), dyeColor+"_brick_wall");
        }
    }

    private static void registerBuildingBlock(Block block, String name) {
		Registry.register(Registry.BLOCK, new Identifier(ColorfulMasonry.MOD_ID, name), block);
        Registry.register(Registry.ITEM, new Identifier(ColorfulMasonry.MOD_ID, name), new BlockItem(block, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
	}
}
