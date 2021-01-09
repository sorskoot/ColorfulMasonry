package net.sorskoot.colorfulmasonry.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import net.minecraft.client.gui.screen.ingame.AbstractFurnaceScreen;
import net.minecraft.client.gui.screen.recipebook.FurnaceRecipeBookScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class MasonryOvenScreen extends AbstractFurnaceScreen<MasonryOvenScreenHandler> {
    // You can replace the background with whatever you like, just remember there
    // will always be the recipe book button
    private static final Identifier BACKGROUND = new Identifier("textures/gui/container/furnace.png");

    public MasonryOvenScreen(MasonryOvenScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, new FurnaceRecipeBookScreen(), inventory, title, BACKGROUND);
    }
}
