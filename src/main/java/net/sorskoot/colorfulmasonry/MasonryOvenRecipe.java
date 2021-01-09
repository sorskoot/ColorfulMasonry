package net.sorskoot.colorfulmasonry;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.sorskoot.colorfulmasonry.registry.MasonryOvenBlocks;

public class MasonryOvenRecipe extends AbstractCookingRecipe {

    
    public MasonryOvenRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime)  {
        super(MasonryOvenBlocks.MASONRY_OVEN_RECIPE_TYPE, id, group, input, output, experience, cookTime);
    
    }

    @Override
    public ItemStack getRecipeKindIcon() {
        return new ItemStack(Items.BLACKSTONE);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MasonryOvenBlocks.MASONRY_OVEN_RECIPE_SERIALIZER;
    }
}


