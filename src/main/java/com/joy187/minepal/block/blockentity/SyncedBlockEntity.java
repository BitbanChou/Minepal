//package com.joy187.minepal.block.blockentity;
//
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.network.play.server.SUpdateTileEntityPacket;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.tileentity.TileEntityType;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.nbt.CompoundNBT;
//
//import javax.annotation.Nullable;
//
///**
// * Simple BlockEntity with networking boilerplate.
// */
//public class SyncedBlockEntity extends TileEntity
//{
//    public SyncedBlockEntity(TileEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
//        super(tileEntityTypeIn);
//    }
//
//    @Override
//    @Nullable
//    public SUpdateTileEntityPacket getUpdatePacket() {
//        return this.getUpdatePacket();
//    }
//
//    @Override
//    public CompoundNBT getUpdateTag() {
//        return this.saveMetadata(new CompoundNBT());
//    }
//
//    @Override
//    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
//        load(pkt.getTag());
//    }
//
//    protected void inventoryChanged() {
//        super.setChanged();
//        if (level != null)
//            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
//    }
//
//    private CompoundNBT saveMetadata(CompoundNBT p_189516_1_) {
//        ResourceLocation resourcelocation = TileEntityType.getKey(this.getType());
//        if (resourcelocation == null) {
//            throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
//        } else {
//            p_189516_1_.putString("id", resourcelocation.toString());
//            p_189516_1_.putInt("x", this.worldPosition.getX());
//            p_189516_1_.putInt("y", this.worldPosition.getY());
//            p_189516_1_.putInt("z", this.worldPosition.getZ());
//            //if (this.customTileData != null) p_189516_1_.put("ForgeData", this.customTileData);
//            if (getCapabilities() != null) p_189516_1_.put("ForgeCaps", serializeCaps());
//            return p_189516_1_;
//        }
//    }
//
//}
