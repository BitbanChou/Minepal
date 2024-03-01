package com.joy187.minepal.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.client.audio.SoundSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class AmethystBlock extends Block {
    public AmethystBlock(AbstractBlock.Properties p_151999_) {
        super(p_151999_);
    }

    public void onProjectileHit(World p_220066_1_, BlockState p_220066_2_, BlockRayTraceResult p_220066_3_, ProjectileEntity p_220066_4_) {
        if (!p_220066_1_.isClientSide) {
            BlockPos blockpos = p_220066_3_.getBlockPos();
            p_220066_1_.playSound((PlayerEntity) null, blockpos, SoundEvents.WART_BLOCK_HIT, SoundCategory.BLOCKS, 1.0F, 0.5F + p_220066_1_.random.nextFloat() * 1.2F);
        }
    }

}
