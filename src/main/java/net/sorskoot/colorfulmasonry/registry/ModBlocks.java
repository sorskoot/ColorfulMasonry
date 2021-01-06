package net.sorskoot.colorfulmasonry.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sorskoot.colorfulmasonry.BrickStairsBlock;
import net.sorskoot.colorfulmasonry.ColorfulMasonry;

public class ModBlocks {
    
    public static final Block PINK_BRICKS = new Block(FabricBlockSettings.copy(Blocks.BRICKS));
    public static final BrickStairsBlock PINK_BRICK_STAIRS = new BrickStairsBlock(Blocks.BRICK_STAIRS.getDefaultState(), FabricBlockSettings.copy(Blocks.BRICK_STAIRS));
    public static final SlabBlock PINK_BRICK_SLABS = new SlabBlock(FabricBlockSettings.copy(Blocks.BRICK_SLAB));

    public static void registerBlocks() {
        registerBuildingBlock(PINK_BRICKS, "pink_bricks");
        registerBuildingBlock(PINK_BRICK_SLABS, "pink_brick_slabs");
        registerBuildingBlock(PINK_BRICK_STAIRS, "pink_brick_stairs");        
    }

    private static void registerBuildingBlock(Block block, String name) {
		Registry.register(Registry.BLOCK, new Identifier(ColorfulMasonry.MOD_ID, name), block);
		Registry.register(Registry.ITEM, new Identifier(ColorfulMasonry.MOD_ID, name), new BlockItem(block, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
	}
}
