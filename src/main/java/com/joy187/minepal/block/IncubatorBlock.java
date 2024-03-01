package com.joy187.minepal.block;


import com.joy187.minepal.block.blockentity.IncubatorBlockEntity;
import com.joy187.minepal.init.BlockEntityInit;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;


import javax.annotation.Nullable;

public class IncubatorBlock extends ContainerBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public IncubatorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.valueOf(false)));
    }

    private static final VoxelShape SHAPE =  Block.box(0, 0, 0, 16, 15, 16);

    @Override
    public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
        return SHAPE;
    }

    /* FACING */

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING,LIT);
    }


//
//    public RenderShape getRenderShape(BlockState pState) {
//        return RenderShape.MODEL;
//    }
    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, World  pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            TileEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof IncubatorBlockEntity) {
                ((IncubatorBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public ActionResultType use(BlockState pState, World  pLevel, BlockPos pPos,
                                PlayerEntity pPlayer, Hand pHand, BlockRayTraceResult pHit) {
        if (!pLevel.isClientSide()) {
            TileEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof IncubatorBlockEntity) {
                NetworkHooks.openGui(((ServerPlayerEntity)pPlayer), (IncubatorBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return ActionResultType.sidedSuccess(pLevel.isClientSide());
    }

//    @Nullable
//    @Override
//    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
//        return new IncubatorBlockEntity(pPos, pState);
//    }

//    @Nullable
//    @Override
//    public <T extends TileEntity TileEntityTicker<T> getTicker(World pLevel, BlockState pState, TileEntityType<T> pBlockEntityType) {
//        return createTickerHelper(pBlockEntityType, BlockEntityInit.INCUBATOR_BLOCK_ENTITY.get(),
//                IncubatorBlockEntity::tick);
//    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
        return new IncubatorBlockEntity();
    }
}
