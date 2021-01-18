package net.sorskoot.colorfulmasonry.gui;

import javax.swing.Painter;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.FurnaceFuelSlot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.sorskoot.colorfulmasonry.ColorfulMasonry;
import net.sorskoot.colorfulmasonry.registry.MasonryOvenBlocks;

public class MasonryOvenController extends SyncedGuiDescription {

    private static final int INVENTORY_SIZE = 7;

    public MasonryOvenController(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(MasonryOvenBlocks.SCREEN_HANDLER_TYPE, syncId, playerInventory,
                getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context, 4));

        WGridPanel root = new WGridPanel(1);
        setRootPanel(root);
        root.setSize(176, 166);
        
        int gridsize = 18;

        WItemSlot fuelSlot = WItemSlot.of(blockInventory, 5);
        fuelSlot.setFilter(i -> AbstractFurnaceBlockEntity.canUseAsFuel(i));
        root.add(fuelSlot, 4*gridsize, 3*gridsize);

        WItemSlot itemSlotDye = WItemSlot.of(blockInventory, 0);
        itemSlotDye.setFilter(i -> i.getItem() instanceof DyeItem);
        root.add(itemSlotDye, 4*gridsize, 1*gridsize);

        WItemSlot itemSlot2 = WItemSlot.of(blockInventory, 1);
        //itemSlot2.setFilter(i -> i.getItem() == Items.CLAY_BALL);
        root.add(itemSlot2, 1*gridsize, 1*gridsize);

        WItemSlot itemSlot3 = WItemSlot.of(blockInventory, 2);
      //  itemSlot3.setFilter(i -> i.getItem() == Items.CLAY_BALL);
        root.add(itemSlot3, 1*gridsize, 2*gridsize);

        WItemSlot itemSlot4 = WItemSlot.of(blockInventory, 3);
        //itemSlot4.setFilter(i -> i.getItem() == Items.CLAY_BALL);
        root.add(itemSlot4, 2*gridsize, 1*gridsize);

        WItemSlot itemSlot5 = WItemSlot.of(blockInventory, 4);
      //  itemSlot5.setFilter(i -> i.getItem() == Items.CLAY_BALL);
        root.add(itemSlot5, 2*gridsize, 2*gridsize);

        WItemSlot outputItemSlot = WItemSlot.of(blockInventory, 6).setInsertingAllowed(false);
        root.add(outputItemSlot, 8*gridsize, 1*gridsize);

        WBar fuel = new WBar(new Identifier(ColorfulMasonry.MOD_ID, "textures/gui/fuelburn_0.png"), 
                             new Identifier(ColorfulMasonry.MOD_ID, "textures/gui/fuelburn_1.png"), 0, 1, WBar.Direction.UP);
        root.add(fuel, 4*18, 2*18,18,18);

        WBar progress = new WBar(new Identifier(ColorfulMasonry.MOD_ID, "textures/gui/progress_0.png"), 
                                 new Identifier(ColorfulMasonry.MOD_ID, "textures/gui/progress_1.png"), 2, 3, WBar.Direction.RIGHT);
        root.add(progress, 108, 36, 24, 18);
        
        root.add(this.createPlayerInventoryPanel(), 0*gridsize, 4*gridsize);

        root.validate(this);
    }

    @Override
    public ItemStack onSlotClick(int slotNumber, int button, SlotActionType action, PlayerEntity player) {
        
        return super.onSlotClick(slotNumber, button, action, player);
    }

    @Override
    public void onContentChanged(Inventory inventory) {
                
        super.onContentChanged(inventory);
    }
    
}
