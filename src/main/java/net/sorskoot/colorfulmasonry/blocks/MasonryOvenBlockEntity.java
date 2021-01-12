package net.sorskoot.colorfulmasonry.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.recipe.RecipeUnlocker;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.sorskoot.colorfulmasonry.gui.MasonryOvenController;
import net.sorskoot.colorfulmasonry.registry.MasonryOvenBlocks;

public class MasonryOvenBlockEntity extends BlockEntity
        implements ImplementedInventory, NamedScreenHandlerFactory, RecipeUnlocker, RecipeInputProvider, Tickable {

    public MasonryOvenBlockEntity() {
        super(MasonryOvenBlocks.MASONRY_OVEN_BLOCK_ENTITY);
    }

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(7, ItemStack.EMPTY);

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return pos.isWithinDistance(player.getBlockPos(), 4.5);
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag, items);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag, items);
        return super.toTag(tag);
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new MasonryOvenController(syncId, inv, ScreenHandlerContext.create(world, pos));
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub

    }

    @Override
    public void provideRecipeInputs(RecipeFinder finder) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setLastRecipe(Recipe<?> recipe) {
        // TODO Auto-generated method stub

    }

    
    @Override
    public Recipe<?> getLastRecipe() {
        // TODO Auto-generated method stub
        return null;
    }
}
