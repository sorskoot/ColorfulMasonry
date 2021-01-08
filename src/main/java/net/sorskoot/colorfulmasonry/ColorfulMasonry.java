package net.sorskoot.colorfulmasonry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.model.BlockStateVariant;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sorskoot.colorfulmasonry.registry.ModBlocks;

public class ColorfulMasonry implements ModInitializer {

    public static final String MOD_ID = "colorfulmasonry";

    // public static final Item EXAMPLE_ITEM = new Item(new Item.Settings());
    // public static final Block BRICKS = new
    // Block(FabricBlockSettings.copy(Blocks.BRICKS)); //new
    // Block(FabricBlockSettings.copy(Blocks.BRICKS));

    @Override
    public void onInitialize() {
        ModBlocks.registerBlocks();
        // Registry.register(Registry.BLOCK, new Identifier(ColorfulMasonry.MOD_ID,
        // "pink_bricks"), BRICKS);
        // Registry.register(Registry.ITEM, new Identifier(ColorfulMasonry.MOD_ID,
        // "pink_bricks"), new BlockItem(BRICKS, new
        // Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        
        
    }

    // Dynamic model generation:
    // https://fabricmc.net/wiki/tutorial:dynamic_model_generation

}
