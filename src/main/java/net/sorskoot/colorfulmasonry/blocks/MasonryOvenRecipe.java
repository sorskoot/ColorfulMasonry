package net.sorskoot.colorfulmasonry.blocks;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.sorskoot.colorfulmasonry.registry.MasonryOvenBlocks;

public class MasonryOvenRecipe implements Recipe<Inventory> {

    private final Ingredient dye;
    private final ItemStack result;
    private final Identifier id;
    private final float xp;
    private final int cookingTime;

    public MasonryOvenRecipe(Identifier id, Ingredient dye, ItemStack result, float xp, int cookingTime) {
        this.id = id;
        this.dye = dye;
        this.result = result;
        this.xp = xp;
        this.cookingTime = cookingTime;
    }

    @Override
    public boolean matches(Inventory inv, World world) {
        return this.dye.test(inv.getStack(0));
    }

    @Override
    public ItemStack craft(Inventory inv) {
        ItemStack itemStack = this.result.copy();
        CompoundTag compoundTag = inv.getStack(0).getTag();
        if (compoundTag != null) {
            itemStack.setTag(compoundTag.copy());
        }

        return itemStack;
    }

    @Environment(EnvType.CLIENT)
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    public ItemStack getOutput() {
        return this.result;
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getRecipeKindIcon() {
        return new ItemStack(MasonryOvenBlocks.MASONRY_OVEN_BLOCK);
    }

    public Identifier getId() {
        return this.id;
    }

    public RecipeSerializer<?> getSerializer() {
        return MasonryOvenBlocks.MASONRY_OVEN_RECIPE_SERIALIZER;
    }

    public RecipeType<?> getType() {
        return MasonryOvenBlocks.MASONRY_OVEN_RECIPE_TYPE;
    }

    public static class Serializer implements RecipeSerializer<MasonryOvenRecipe> {
        private final int cookingTime = 200;

        @Override
        public MasonryOvenRecipe read(Identifier id, JsonObject json) {
            Ingredient ingredient = Ingredient.fromJson(JsonHelper.getObject(json, "ingredient"));
            // Ingredient ingredient = Ingredient.fromJson(JsonHelper.getObject(json,
            // "dye"));
            String string2 = JsonHelper.getString(json, "result");
            Identifier identifier2 = new Identifier(string2);
            ItemStack itemStack = new ItemStack(Registry.ITEM.getOrEmpty(identifier2).orElseThrow(() -> {
                return new IllegalStateException("Item: " + string2 + " does not exist");
            }));
            ;
            float f = JsonHelper.getFloat(json, "experience", 0.0F);
            int i = JsonHelper.getInt(json, "cookingtime", this.cookingTime);
            return new MasonryOvenRecipe(id, ingredient, itemStack, f, i);
        }

        @Override
        public MasonryOvenRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient ingredient = Ingredient.fromPacket(buf);
            ItemStack itemStack = buf.readItemStack();
            float f = buf.readFloat();
            int i = buf.readVarInt();
            return new MasonryOvenRecipe(id, ingredient, itemStack, f, i);
        }

        @Override
        public void write(PacketByteBuf buf, MasonryOvenRecipe recipe) {
            recipe.dye.write(buf);
            buf.writeItemStack(recipe.result);
            buf.writeFloat(recipe.xp);
            buf.writeVarInt(recipe.cookingTime);
        }

    }
}
