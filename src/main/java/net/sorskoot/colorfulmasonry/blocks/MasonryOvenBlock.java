package net.sorskoot.colorfulmasonry.blocks;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class MasonryOvenBlock extends  Block implements BlockEntityProvider {
    public static final DirectionProperty FACING;
    public static final BooleanProperty LIT;
    
    static {
        FACING = HorizontalFacingBlock.FACING;
        LIT = Properties.LIT;
     }

    public MasonryOvenBlock() {
        super(FabricBlockSettings.copy(Blocks.COBBLESTONE));
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(LIT, false));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockView world) {
        return new MasonryOvenBlockEntity();
    }
    
    
    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        player.openHandledScreen(blockState.createScreenHandlerFactory(world, blockPos));
        
        world.setBlockState(blockPos, blockState.with(LIT, Boolean.valueOf(true)), 3);

        // if (world.isClient) return ActionResult.FAIL;
        // Inventory blockEntity = (Inventory) world.getBlockEntity(blockPos);
        
 
        // if (!player.getStackInHand(hand).isEmpty()) {
        //     // Check what is the first open slot and put an item from the player's hand there
        //     if (blockEntity.getStack(0).isEmpty()) {
        //         // Put the stack the player is holding into the inventory
        //         blockEntity.setStack(0, player.getStackInHand(hand).copy());
        //         // Remove the stack from the player's hand
        //         player.getStackInHand(hand).setCount(0);
        //     } else if (blockEntity.getStack(1).isEmpty()) {
        //         blockEntity.setStack(1, player.getStackInHand(hand).copy());
        //         player.getStackInHand(hand).setCount(0);
        //     } else {
        //         // If the inventory is full we'll print it's contents
        //         System.out.println("The first slot holds "
        //                 + blockEntity.getStack(0) + " and the second slot holds " + blockEntity.getStack(1));
        //     }
        // } 
        return ActionResult.SUCCESS;
    }

    @Override
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return world.getBlockEntity(pos) instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory) world.getBlockEntity(pos) : null;        
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        // if (itemStack.hasCustomName()) {
        //     BlockEntity blockEntity = world.getBlockEntity(pos);
        //     if (blockEntity instanceof MasonryOvenBlockEntity) {
        //        ((MasonryOvenBlockEntity)blockEntity).setCustomName(itemStack.getName());
        //     }
        //  }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        // if (!state.isOf(newState.getBlock())) {
        //     BlockEntity blockEntity = world.getBlockEntity(pos);
        //     if (blockEntity instanceof MasonryOvenBlockEntity) {
        //        ItemScatterer.spawn(world, (BlockPos)pos, (Inventory)((MasonryOvenBlockEntity)blockEntity));
        //        ((MasonryOvenBlockEntity)blockEntity).method_27354(world, Vec3d.ofCenter(pos));
        //        world.updateComparators(pos, this);
        //     }
   
             super.onStateReplaced(state, world, pos, newState, moved);
        //  }
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }
    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }
    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }
    
}
