package net.sorskoot.colorfulmasonry.blocks;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;
import net.sorskoot.colorfulmasonry.registry.MasonryOvenBlocks;

public class MasonryOvenScreenHandler extends AbstractFurnaceScreenHandler {
    public MasonryOvenScreenHandler(int i, PlayerInventory playerInventory) {
        super(MasonryOvenBlocks.MASONRY_OVEN_SCREEN_HANDLER, MasonryOvenBlocks.MASONRY_OVEN_RECIPE_TYPE,
                RecipeBookCategory.FURNACE, i, playerInventory);
    }

    public MasonryOvenScreenHandler(int i, PlayerInventory playerInventory, Inventory inventory,
            PropertyDelegate propertyDelegate) {
        super(MasonryOvenBlocks.MASONRY_OVEN_SCREEN_HANDLER, MasonryOvenBlocks.MASONRY_OVEN_RECIPE_TYPE,
                RecipeBookCategory.FURNACE, i, playerInventory, inventory, propertyDelegate);
    }
}
