package net.sorskoot.colorfulmasonry.gui;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.sorskoot.colorfulmasonry.ColorfulMasonry;

public class MasonryOvenScreen extends HandledScreen<ScreenHandler> {
    
    private static final Identifier TEXTURE = new Identifier(ColorfulMasonry.MOD_ID, "textures/gui/masonry_oven.png");

    public MasonryOvenScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }
    // public MasonryOvenScreen(MasonryOvenController description, PlayerEntity player, Text text) {
    //     super(description, player, text);
    // }

    // public MasonryOvenScreen(MasonryOvenController description, PlayerInventory invent, Text text) {
    //     super(description, invent.player, text);
    // }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.client.getTextureManager().bindTexture(TEXTURE);
        int i = this.x;
        int j = this.y;
        this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        int l;
        if (((MasonryOvenScreenHandler)this.handler).isBurning()) {
           l = ((MasonryOvenScreenHandler)this.handler).getFuelProgress();
           this.drawTexture(matrices, i + 74, j + 36 + 12 - l, 176, 12 - l, 14, l + 1);
        }
  
        l = ((MasonryOvenScreenHandler)this.handler).getCookProgress();
        this.drawTexture(matrices, i + 97, j + 34, 176, 14, l + 1, 16);
    }
 
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
 
    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;

    }
}
