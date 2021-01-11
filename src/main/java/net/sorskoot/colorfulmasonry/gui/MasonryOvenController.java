package net.sorskoot.colorfulmasonry.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.sorskoot.colorfulmasonry.registry.MasonryOvenBlocks;

public class MasonryOvenController extends SyncedGuiDescription  {
    
    private static final int INVENTORY_SIZE = 1;

    public MasonryOvenController(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(MasonryOvenBlocks.SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 200);

        WItemSlot itemSlot = WItemSlot.of(blockInventory, 0);
        root.add(itemSlot, 4, 1);

        root.add(this.createPlayerInventoryPanel(), 0, 3);

        root.validate(this);
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        
        super.onContentChanged(inventory);
    }
    
}
