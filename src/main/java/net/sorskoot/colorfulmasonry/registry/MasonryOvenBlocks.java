package net.sorskoot.colorfulmasonry.registry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sorskoot.colorfulmasonry.ColorfulMasonry;
import net.sorskoot.colorfulmasonry.MasonryOvenRecipe;
import net.sorskoot.colorfulmasonry.blocks.MasonryOvenBlock;
import net.sorskoot.colorfulmasonry.blocks.MasonryOvenBlockEntity;
import net.sorskoot.colorfulmasonry.blocks.MasonryOvenScreenHandler;

public class MasonryOvenBlocks {
    public static final String BLOCK_ID = "masonry_oven";
    public static final Block MASONRY_OVEN_BLOCK = new MasonryOvenBlock();
    
    public static final RecipeType<MasonryOvenRecipe> MASONRY_OVEN_RECIPE_TYPE = new RecipeType<MasonryOvenRecipe>() {
        @Override
        public String toString() {
            return BLOCK_ID;
        }
    };

    public static final RecipeSerializer<MasonryOvenRecipe> MASONRY_OVEN_RECIPE_SERIALIZER =  new CookingRecipeSerializer<>(MasonryOvenRecipe::new, 200);
    public static final BlockEntityType MASONRY_OVEN_BLOCK_ENTITY = BlockEntityType.Builder.create(MasonryOvenBlockEntity::new, MASONRY_OVEN_BLOCK).build(null);
    public static final ScreenHandlerType<MasonryOvenScreenHandler> 
    MASONRY_OVEN_SCREEN_HANDLER = 
        ScreenHandlerRegistry.registerSimple(new Identifier(ColorfulMasonry.MOD_ID, BLOCK_ID), MasonryOvenScreenHandler::new);

    public static void registerBlocks() {
        
        Registry.register(Registry.RECIPE_TYPE, new Identifier(ColorfulMasonry.MOD_ID, BLOCK_ID), MASONRY_OVEN_RECIPE_TYPE);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(ColorfulMasonry.MOD_ID, BLOCK_ID),MASONRY_OVEN_BLOCK_ENTITY);

        Registry.register(Registry.BLOCK, new Identifier(ColorfulMasonry.MOD_ID, BLOCK_ID), MASONRY_OVEN_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(ColorfulMasonry.MOD_ID, BLOCK_ID),
                new BlockItem(MASONRY_OVEN_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

    }
}