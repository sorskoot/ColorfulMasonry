package net.sorskoot.colorfulmasonry.blocks;

import java.util.Iterator;

import org.jetbrains.annotations.Nullable;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.RecipeUnlocker;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.sorskoot.colorfulmasonry.gui.MasonryOvenController;
import net.sorskoot.colorfulmasonry.registry.MasonryOvenBlocks;

public class MasonryOvenBlockEntity extends BlockEntity
        implements PropertyDelegateHolder, SidedInventory, NamedScreenHandlerFactory, RecipeUnlocker, RecipeInputProvider, Tickable {

    public static final int SLOT_FUEL = 5;
    public static final int SLOT_DYE = 0;
    public static final int SLOT_GM1 = 1; // ground material (GM) 1
    public static final int SLOT_GM2 = 2;
    public static final int SLOT_GM3 = 3;
    public static final int SLOT_GM4 = 4;
    public static final int SLOT_OUTPUT = 6;

    // TODO: Make this value configurable
    private static final int SPEEDACCELERATION = 2;

    private int burnTime;
    private int fuelTime;
    private int cookTime;
    private int cookTimeTotal;

    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private final Object2IntOpenHashMap<Identifier> recipesUsed;
    protected final RecipeType<MasonryOvenRecipe> recipeType;

    public MasonryOvenBlockEntity() {
        super(MasonryOvenBlocks.MASONRY_OVEN_BLOCK_ENTITY);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0:
                        return MasonryOvenBlockEntity.this.burnTime;
                    case 1:
                        return MasonryOvenBlockEntity.this.fuelTime;
                    case 2:
                        return MasonryOvenBlockEntity.this.cookTime;
                    case 3:
                        return MasonryOvenBlockEntity.this.cookTimeTotal;
                    default:
                        return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        MasonryOvenBlockEntity.this.burnTime = value;
                        break;
                    case 1:
                        MasonryOvenBlockEntity.this.fuelTime = value;
                        break;
                    case 2:
                        MasonryOvenBlockEntity.this.cookTime = value;
                        break;
                    case 3:
                        MasonryOvenBlockEntity.this.cookTimeTotal = value;
                }

            }

            public int size() {
                return 4;
            }
        };
        this.recipesUsed = new Object2IntOpenHashMap();
        this.recipeType = MasonryOvenBlocks.MASONRY_OVEN_RECIPE_TYPE;
    }

    private boolean isBurning() {
        return this.burnTime > 0;
    }

    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.fromTag(tag, this.inventory);
        this.burnTime = tag.getShort("BurnTime");
        this.cookTime = tag.getShort("CookTime");
        this.cookTimeTotal = tag.getShort("CookTimeTotal");
        this.fuelTime = this.getFuelTime((ItemStack) this.inventory.get(1));
        CompoundTag compoundTag = tag.getCompound("RecipesUsed");
        Iterator var4 = compoundTag.getKeys().iterator();

        while (var4.hasNext()) {
            String string = (String) var4.next();
            this.recipesUsed.put(new Identifier(string), compoundTag.getInt(string));
        }

    }

    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putShort("BurnTime", (short) this.burnTime);
        tag.putShort("CookTime", (short) this.cookTime);
        tag.putShort("CookTimeTotal", (short) this.cookTimeTotal);
        Inventories.toTag(tag, this.inventory);
        CompoundTag compoundTag = new CompoundTag();
        this.recipesUsed.forEach((identifier, integer) -> {
            compoundTag.putInt(identifier.toString(), integer);
        });
        tag.put("RecipesUsed", compoundTag);
        return tag;
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
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == SLOT_FUEL) {
            // ItemStack itemStack = (ItemStack)this.inventory.get(1);
            return AbstractFurnaceBlockEntity.canUseAsFuel(stack);// || stack.getItem() == Items.BUCKET &&
                                                                  // itemStack.getItem() != Items.BUCKET;
        } else if (slot == SLOT_DYE) {
            return stack.getItem() instanceof DyeItem;
        }
        return true;// stack.getItem() == Items.CLAY_BALL;
    }

    @Override
    public void tick() {
        boolean bl = this.isBurning();
        boolean bl2 = false;
        if (this.isBurning()) {
            --this.burnTime;
        }
        if (!this.world.isClient) {
            ItemStack fuelItemStack = (ItemStack) this.inventory.get(SLOT_FUEL);
            if (!this.isBurning() && (fuelItemStack.isEmpty() || ((ItemStack) this.inventory.get(0)).isEmpty())) {
                if (!this.isBurning() && this.cookTime > 0) {
                    this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
                }
            } else {
                Recipe<?> recipe = (Recipe) this.world.getRecipeManager()
                        .getFirstMatch(this.recipeType, this, this.world).orElse((MasonryOvenRecipe) null);
                if (!this.isBurning() && this.canAcceptRecipeOutput(recipe)) {
                    this.burnTime = this.getFuelTime(fuelItemStack);
                    this.fuelTime = this.burnTime;
                    if (this.isBurning()) {
                        bl2 = true;
                        if (!fuelItemStack.isEmpty()) {
                            Item item = fuelItemStack.getItem();
                            fuelItemStack.decrement(1);
                            if (fuelItemStack.isEmpty()) {
                                Item item2 = item.getRecipeRemainder();
                                this.inventory.set(1, item2 == null ? ItemStack.EMPTY : new ItemStack(item2));
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canAcceptRecipeOutput(recipe)) {
                    ++this.cookTime;
                    if (this.cookTime == this.cookTimeTotal) {
                        this.cookTime = 0;
                        this.cookTimeTotal = this.getCookTime();
                        this.craftRecipe(recipe);
                        bl2 = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            }

            if (bl != this.isBurning()) {
                bl2 = true;
                this.world.setBlockState(this.pos,
                        (BlockState) this.world.getBlockState(this.pos).with(MasonryOvenBlock.LIT, this.isBurning()),
                        3);
            }
        }

        if (bl2) {
            this.markDirty();
        }
    }

    protected boolean canAcceptRecipeOutput(@Nullable Recipe<?> recipe) {
        if (
            !((ItemStack)this.inventory.get(SLOT_DYE)).isEmpty() && 
            !((ItemStack)this.inventory.get(SLOT_GM1)).isEmpty() && 
            !((ItemStack)this.inventory.get(SLOT_GM2)).isEmpty() && 
            !((ItemStack)this.inventory.get(SLOT_GM3)).isEmpty() && 
            !((ItemStack)this.inventory.get(SLOT_GM4)).isEmpty() && 
            recipe != null) {

           ItemStack recipeOutputItemStack = recipe.getOutput();
           if (recipeOutputItemStack.isEmpty()) {
              return false;
           } else {
              ItemStack ouputItemStack = (ItemStack)this.inventory.get(SLOT_OUTPUT);
              if (ouputItemStack.isEmpty()) {
                 return true;
              } else if (!ouputItemStack.isItemEqualIgnoreDamage(recipeOutputItemStack)) {
                 return false;
              } else if (ouputItemStack.getCount() < this.getMaxCountPerStack() && ouputItemStack.getCount() < ouputItemStack.getMaxCount()) {
                 return true;
              } else {
                 return ouputItemStack.getCount() < recipeOutputItemStack.getMaxCount();
              }
           }
        } else {
           return false;
        }
     }
  
     private void craftRecipe(@Nullable Recipe<?> recipe) {
        if (recipe != null && this.canAcceptRecipeOutput(recipe)) {
           ItemStack dyeItemStack = (ItemStack)this.inventory.get(SLOT_DYE);
           ItemStack groundMaterial1ItemStack = (ItemStack)this.inventory.get(SLOT_GM1);
           ItemStack groundMaterial2ItemStack = (ItemStack)this.inventory.get(SLOT_GM2);
           ItemStack groundMaterial3ItemStack = (ItemStack)this.inventory.get(SLOT_GM3);
           ItemStack groundMaterial4ItemStack = (ItemStack)this.inventory.get(SLOT_GM4);
           ItemStack outputItemStack = (ItemStack)this.inventory.get(SLOT_OUTPUT);
           ItemStack recepeOutputItemStack = recipe.getOutput();
           if (outputItemStack.isEmpty()) {
              this.inventory.set(SLOT_OUTPUT, recepeOutputItemStack.copy());
           } else if (outputItemStack.getItem() == recepeOutputItemStack.getItem()) {
            outputItemStack.increment(1);
           }
  
           if (!this.world.isClient) {
              this.setLastRecipe(recipe);
           }
          
           dyeItemStack.decrement(1);
           groundMaterial1ItemStack.decrement(1);
           groundMaterial2ItemStack.decrement(1);
           groundMaterial3ItemStack.decrement(1);
           groundMaterial4ItemStack.decrement(1);
        }
     }

    @Override
    public void provideRecipeInputs(RecipeFinder finder) {
        Iterator var2 = this.inventory.iterator();

        while (var2.hasNext()) {
            ItemStack itemStack = (ItemStack) var2.next();
            finder.addItem(itemStack);
        }
    }

    @Override
    public void setLastRecipe(Recipe<?> recipe) {
        if (recipe != null) {
            Identifier identifier = recipe.getId();
            this.recipesUsed.addTo(identifier, 1);
        }
    }

    @Override
    public Recipe<?> getLastRecipe() {
        return null;
    }

    /**
     * Calculates the time a type of fuel burns
     * 
     * @param fuel
     * @return the time the fuel burn
     */
    private int getFuelTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        } else {
            Item item = fuel.getItem();
            return (Integer) AbstractFurnaceBlockEntity.createFuelTimeMap().getOrDefault(item, 0) / SPEEDACCELERATION;
        }
    }

    @Override
    public int size() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        Iterator var1 = this.inventory.iterator();

        ItemStack itemStack;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            itemStack = (ItemStack) var1.next();
        } while (itemStack.isEmpty());

        return false;
    }

    @Override
    public ItemStack getStack(int slot) {
        return (ItemStack) this.inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(this.inventory, slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.inventory, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        ItemStack itemStack = (ItemStack) this.inventory.get(slot);
        boolean bl = !stack.isEmpty() && stack.isItemEqualIgnoreDamage(itemStack)
                && ItemStack.areTagsEqual(stack, itemStack);
        this.inventory.set(slot, stack);
        if (stack.getCount() > this.getMaxCountPerStack()) {
            stack.setCount(this.getMaxCountPerStack());
        }

        if (slot == 0 && !bl) {
            this.cookTimeTotal = this.getCookTime();
            this.cookTime = 0;
            this.markDirty();
        }
    }

    protected int getCookTime() {
        return (Integer) this.world.getRecipeManager().getFirstMatch(this.recipeType, this, this.world)
                .map(MasonryOvenRecipe::getCookTime).orElse(200);
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world.getBlockEntity(this.pos) != this) {
            return false;
        } else {
            return player.squaredDistanceTo((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
                    (double) this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, Direction dir) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public PropertyDelegate getPropertyDelegate() {        
        return propertyDelegate;
    }
}
