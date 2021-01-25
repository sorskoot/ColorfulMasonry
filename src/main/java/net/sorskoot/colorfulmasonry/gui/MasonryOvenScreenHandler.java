package net.sorskoot.colorfulmasonry.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.BlastFurnaceScreenHandler;
import net.minecraft.screen.FurnaceScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.sorskoot.colorfulmasonry.blocks.MasonryOvenBlockEntity;
import net.sorskoot.colorfulmasonry.registry.MasonryOvenBlocks;

public class MasonryOvenScreenHandler extends ScreenHandler { // SyncedGuiDescription {

  private final ScreenHandlerContext context;

  private Runnable inventoryChangeListener;
  private final Inventory inventory;
  private final Slot dyeSlot;
  private final Slot[] claySlot = new Slot[4];
  private final Slot fuelSlot;

  private final Slot outputSlot;

  private final PropertyDelegate propertyDelegate;

  public MasonryOvenScreenHandler(int syncId, PlayerInventory playerInventory) {
    this(syncId, playerInventory, new SimpleInventory(7), ScreenHandlerContext.EMPTY, new ArrayPropertyDelegate(4));
  }

  public MasonryOvenScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory,
      final ScreenHandlerContext context, PropertyDelegate propertyDelegate) {
    super(MasonryOvenBlocks.SCREEN_HANDLER_TYPE, syncId);
    this.inventoryChangeListener = () -> {
    };
    this.propertyDelegate = propertyDelegate;
    this.inventory = inventory;
    // this.input = new SimpleInventory(6) {
    // public void markDirty() {
    // super.markDirty();
    // MasonryOvenScreenHandler.this.onContentChanged(this);
    // MasonryOvenScreenHandler.this.inventoryChangeListener.run();
    // }
    // };

    // this.output = new SimpleInventory(1) {
    // public void markDirty() {
    // super.markDirty();
    // MasonryOvenScreenHandler.this.inventoryChangeListener.run();
    // }
    // };
    this.dyeSlot = this.addSlot(new Slot(this.inventory, MasonryOvenBlockEntity.SLOT_DYE, 74, 17) {
      public boolean canInsert(ItemStack stack) {
        return stack.getItem() instanceof DyeItem;
      }
    });
    this.claySlot[0] = this.addSlot(new Slot(this.inventory, MasonryOvenBlockEntity.SLOT_GM1, 20, 17));
    this.claySlot[1] = this.addSlot(new Slot(this.inventory, MasonryOvenBlockEntity.SLOT_GM2, 38, 17));
    this.claySlot[2] = this.addSlot(new Slot(this.inventory, MasonryOvenBlockEntity.SLOT_GM3, 20, 35));
    this.claySlot[3] = this.addSlot(new Slot(this.inventory, MasonryOvenBlockEntity.SLOT_GM4, 38, 35));

    this.fuelSlot = this.addSlot(new Slot(this.inventory, MasonryOvenBlockEntity.SLOT_FUEL, 74, 53) {
      public boolean canInsert(ItemStack stack) {
        return AbstractFurnaceBlockEntity.canUseAsFuel(stack);
      }
    });

    this.outputSlot = this.addSlot(new Slot(this.inventory, MasonryOvenBlockEntity.SLOT_OUTPUT, 134, 35) {
      public boolean canInsert(ItemStack stack) {
        return false;
      }

      public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {
        // LoomScreenHandler.this.bannerSlot.takeStack(1);
        // LoomScreenHandler.this.dyeSlot.takeStack(1);
        // if (!LoomScreenHandler.this.bannerSlot.hasStack() ||
        // !LoomScreenHandler.this.dyeSlot.hasStack()) {
        // LoomScreenHandler.this.selectedPattern.set(0);
        // }

        // context.run((world, blockPos) -> {
        // long l = world.getTime();
        // if (LoomScreenHandler.this.lastTakeResultTime != l) {
        // world.playSound((PlayerEntity)null, blockPos,
        // SoundEvents.UI_LOOM_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F);
        // LoomScreenHandler.this.lastTakeResultTime = l;
        // }

        // });
        return super.onTakeItem(player, stack);
      }
    });

    this.context = context;
    int k;
    for (k = 0; k < 3; ++k) {
      for (int j = 0; j < 9; ++j) {
        this.addSlot(new Slot(playerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
      }
    }

    for (k = 0; k < 9; ++k) {
      this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
    }

    this.addProperties(propertyDelegate);

    // super(MasonryOvenBlocks.SCREEN_HANDLER_TYPE, syncId, playerInventory,
    // getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context,
    // 4));

    // WGridPanel root = new WGridPanel(1);
    // setRootPanel(root);
    // root.setSize(176, 166);

    // int gridsize = 18;

    // WItemSlot fuelSlot = WItemSlot.of(blockInventory, 5);
    // fuelSlot.setFilter(i -> AbstractFurnaceBlockEntity.canUseAsFuel(i));
    // root.add(fuelSlot, 4*gridsize, 3*gridsize);

    // WItemSlot itemSlotDye = WItemSlot.of(blockInventory, 0);
    // itemSlotDye.setFilter(i -> i.getItem() instanceof DyeItem);
    // root.add(itemSlotDye, 4*gridsize, 1*gridsize);

    // WItemSlot itemSlot2 = WItemSlot.of(blockInventory, 1);

    // root.add(itemSlot2, 1*gridsize, 1*gridsize);

    // WItemSlot itemSlot3 = WItemSlot.of(blockInventory, 2);

    // root.add(itemSlot3, 1*gridsize, 2*gridsize);

    // WItemSlot itemSlot4 = WItemSlot.of(blockInventory, 3);
    // //itemSlot4.setFilter(i -> i.getItem() == Items.CLAY_BALL);
    // root.add(itemSlot4, 2*gridsize, 1*gridsize);

    // WItemSlot itemSlot5 = WItemSlot.of(blockInventory, 4);
    // // itemSlot5.setFilter(i -> i.getItem() == Items.CLAY_BALL);
    // root.add(itemSlot5, 2*gridsize, 2*gridsize);

    // WItemSlot outputItemSlot = WItemSlot.of(blockInventory,
    // 6).setInsertingAllowed(false);
    // root.add(outputItemSlot, 8*gridsize, 1*gridsize);

    // WBar fuel = new WBar(new Identifier(ColorfulMasonry.MOD_ID,
    // "textures/gui/fuelburn_0.png"),
    // new Identifier(ColorfulMasonry.MOD_ID, "textures/gui/fuelburn_1.png"), 0, 1,
    // WBar.Direction.UP);
    // root.add(fuel, 4*18, 2*18,18,18);

    // WBar progress = new WBar(new Identifier(ColorfulMasonry.MOD_ID,
    // "textures/gui/progress_0.png"),
    // new Identifier(ColorfulMasonry.MOD_ID, "textures/gui/progress_1.png"), 2, 3,
    // WBar.Direction.RIGHT);
    // root.add(progress, 108, 36, 24, 18);

    // root.add(this.createPlayerInventoryPanel(), 0*gridsize, 4*gridsize);

    // root.validate(this);
  }

  @Override
  public ItemStack onSlotClick(int slotNumber, int button, SlotActionType action, PlayerEntity player) {

    return super.onSlotClick(slotNumber, button, action, player);
  }

  @Override
  public void onContentChanged(Inventory inventory) {

    super.onContentChanged(inventory);
  }

  @Environment(EnvType.CLIENT)
  public void setInventoryChangeListener(Runnable inventoryChangeListener) {
    this.inventoryChangeListener = inventoryChangeListener;

  }

  @Override
  public boolean canUse(PlayerEntity player) {
    return canUse(this.context, player, MasonryOvenBlocks.MASONRY_OVEN_BLOCK);
  }

  @Override
  public ItemStack transferSlot(PlayerEntity player, int invSlot) {
    ItemStack newStack = ItemStack.EMPTY;
    Slot slot = this.slots.get(invSlot);
    if (slot != null && slot.hasStack()) {
      ItemStack originalStack = slot.getStack();
      newStack = originalStack.copy();
      if (invSlot == MasonryOvenBlockEntity.SLOT_OUTPUT) {
        if (!this.insertItem(originalStack, 7, 43, true)) {
          return ItemStack.EMPTY;
        }
        slot.onStackChanged(originalStack, newStack);

      } else if (invSlot > 7) {
        if (originalStack.getItem() instanceof DyeItem) {
          if (!this.insertItem(originalStack, MasonryOvenBlockEntity.SLOT_DYE, MasonryOvenBlockEntity.SLOT_DYE + 1,
              false)) {
            return ItemStack.EMPTY;
          }
        } else if (AbstractFurnaceBlockEntity.canUseAsFuel(originalStack)) {
          if (!this.insertItem(originalStack, MasonryOvenBlockEntity.SLOT_FUEL, MasonryOvenBlockEntity.SLOT_FUEL + 1,
              false)) {
            return ItemStack.EMPTY;
          }
        }

      } else if (!this.insertItem(originalStack, 7, 43, false)) {
        return ItemStack.EMPTY;
      }

      if (originalStack.isEmpty()) {
        slot.setStack(ItemStack.EMPTY);
      } else {
        slot.markDirty();
      }

      if (originalStack.getCount() == newStack.getCount()) {
        return ItemStack.EMPTY;
      }

      slot.onTakeItem(player, originalStack);
    }

    return newStack;
  }

  @Environment(EnvType.CLIENT)
  public int getCookProgress() {
    int i = this.propertyDelegate.get(2);
    int j = this.propertyDelegate.get(3);
    return j != 0 && i != 0 ? i * 24 / j : 0;
  }

  @Environment(EnvType.CLIENT)
  public int getFuelProgress() {
    int i = this.propertyDelegate.get(1);
    if (i == 0) {
      i = 200;
    }

    return this.propertyDelegate.get(0) * 13 / i;
  }

  @Environment(EnvType.CLIENT)
  public boolean isBurning() {
    return this.propertyDelegate.get(0) > 0;
  }
}
