package net.sorskoot.colorfulmasonry.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

//@Environment(EnvType.CLIENT)
public class MasonryOvenScreen extends CottonInventoryScreen<MasonryOvenController> {

    public MasonryOvenScreen(MasonryOvenController description, PlayerEntity player, Text text) {
        super(description, player, text);
    }
}
