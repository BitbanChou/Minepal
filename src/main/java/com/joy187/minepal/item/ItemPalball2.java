package com.joy187.minepal.item;

import com.joy187.minepal.Main;
import com.joy187.minepal.entity.EntityPalball;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemPalball2 extends ItemPalball {

    public ItemPalball2(Properties properties) {
        super(properties.durability(10));
    }


    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand) {
        ItemStack itemStackIn = playerIn.getItemInHand(hand);

        if (!worldIn.isClientSide) {
            EntityPalball pokeball = new EntityPalball(playerIn, worldIn, itemStackIn.copy(),2);
            pokeball.shoot(playerIn.getLookAngle().x, playerIn.getLookAngle().y, playerIn.getLookAngle().z, 0.5F, 0.5F);
            worldIn.addFreshEntity(pokeball);
        }

        //worldIn.playSound(playerIn, playerIn.blockPosition(), SoundInit.PELEBALL.get(), SoundSource.PLAYERS, 1.5F, 0.8F / (worldIn.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!playerIn.isCreative() || this.getEntityCompound(itemStackIn) != null) {
            return ActionResult.success(ItemStack.EMPTY);
        }

        return ActionResult.success(itemStackIn);
    }

}


