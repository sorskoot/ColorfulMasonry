package net.sorskoot.colorfulmasonry.blocks;

import java.util.Random;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.ingame.BlastFurnaceScreen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.BlastingRecipe;
import net.minecraft.screen.BlastFurnaceScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stat;
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

public class MasonryOvenBlock extends Block implements BlockEntityProvider {
    public static final DirectionProperty FACING;
    public static final BooleanProperty LIT;

    static {
        FACING = HorizontalFacingBlock.FACING;
        LIT = Properties.LIT;
    }

    public MasonryOvenBlock() {
        super(FabricBlockSettings.copy(Blocks.FURNACE));
        this.setDefaultState((BlockState) ((BlockState) ((BlockState) this.stateManager.getDefaultState()).with(FACING,
                Direction.NORTH)).with(LIT, false));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockView world) {
        return new MasonryOvenBlockEntity();
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand,
            BlockHitResult blockHitResult) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
           // player.openHandledScreen(blockState.createScreenHandlerFactory(world, blockPos));
            BlockEntity blockEntity = world.getBlockEntity(blockPos);
            if (blockEntity instanceof MasonryOvenBlockEntity) {
               player.openHandledScreen((NamedScreenHandlerFactory)blockEntity);
               // TODO Add stats for using the masonry oven
            }
            return ActionResult.CONSUME;
        }
    }

    // @Override
    // public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
    //     return world.getBlockEntity(pos) instanceof NamedScreenHandlerFactory
    //             ? (NamedScreenHandlerFactory) world.getBlockEntity(pos)
    //             : null;
    // }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState) this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        // if (itemStack.hasCustomName()) {
        // BlockEntity blockEntity = world.getBlockEntity(pos);
        // if (blockEntity instanceof MasonryOvenBlockEntity) {
        // ((MasonryOvenBlockEntity)blockEntity).setCustomName(itemStack.getName());
        // }
        // }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        // if (!state.isOf(newState.getBlock())) {
        // BlockEntity blockEntity = world.getBlockEntity(pos);
        // if (blockEntity instanceof MasonryOvenBlockEntity) {
        // ItemScatterer.spawn(world, (BlockPos)pos,
        // (Inventory)((MasonryOvenBlockEntity)blockEntity));
        // ((MasonryOvenBlockEntity)blockEntity).method_27354(world,
        // Vec3d.ofCenter(pos));
        // world.updateComparators(pos, this);
        // }
        
        world.updateComparators(pos, this);
        super.onStateReplaced(state, world, pos, newState, moved);
        // }
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState) state.with(FACING, rotation.rotate((Direction) state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction) state.get(FACING)));
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if ((Boolean) state.get(LIT)) {
            double d = (double) pos.getX() + 0.5D;
            double e = (double) pos.getY();
            double f = (double) pos.getZ() + 0.5D;
            if (random.nextDouble() < 0.1D) {
                world.playSound(d, e, f, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F,
                        false);
            }

            Direction direction = (Direction) state.get(FACING);
            Direction.Axis axis = direction.getAxis();
            double g = 0.52D;
            double h = random.nextDouble() * 0.6D - 0.3D;
            double i = axis == Direction.Axis.X ? (double) direction.getOffsetX() * 0.52D : h;
            double j = random.nextDouble() * 6.0D / 16.0D;
            double k = axis == Direction.Axis.Z ? (double) direction.getOffsetZ() * 0.52D : h;
            world.addParticle(ParticleTypes.SMOKE, d + i, e + j, f + k, 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.FLAME, d + i, e + j, f + k, 0.0D, 0.0D, 0.0D);
        }
    }

    public boolean hasComparatorOutput(BlockState state) {
        return true;
     }
  
     public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
     }
}
