package com.joy187.minepal.item;

import com.joy187.minepal.Main;
import com.joy187.minepal.entity.EntityPalball;
import com.joy187.minepal.init.SoundInit;
import com.joy187.minepal.util.NBTHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraft.world.World;
import net.minecraftforge.common.property.Properties;

import java.util.List;

public class ItemPalball extends Item {

    public ItemPalball(Properties properties) {
        super(properties.durability(10));
    }

//    @Override
//    protected boolean allowedIn(CreativeModeTab group) {
//        return true;
//    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand) {
        ItemStack itemStackIn = playerIn.getItemInHand(hand);

        if (!worldIn.isClientSide) {
            EntityPalball pokeball = new EntityPalball(playerIn, worldIn, itemStackIn.copy(),1);
            pokeball.shoot(playerIn.getLookAngle().x, playerIn.getLookAngle().y, playerIn.getLookAngle().z, 0.5F, 0.5F);
            pokeball.shoot(playerIn.getLookAngle().x, playerIn.getLookAngle().y, playerIn.getLookAngle().z, 0.5F, 0.5F);
            pokeball.shoot(playerIn.getLookAngle().x, playerIn.getLookAngle().y, playerIn.getLookAngle().z, 0.5F, 0.5F);
            worldIn.addFreshEntity(pokeball);
        }

        //worldIn.playSound(playerIn, playerIn.blockPosition(), SoundInit.PELEBALL.get(), SoundSource.PLAYERS, 1.5F, 0.8F / (worldIn.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!playerIn.isCreative() || this.getEntityCompound(itemStackIn) != null) {
            return ActionResult.success(ItemStack.EMPTY);
        }

        return ActionResult.success(itemStackIn);
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT entityTag = this.getEntityCompound(stack);
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.peleball"));
        }else tooltip.add(new TranslationTextComponent("tooltip.shift"));
        if (entityTag != null) {
            String entityName = entityTag.getString("id");
            if (entityTag.contains("peleball_name")) {
                entityName = entityTag.getString("peleball_name");
            }

            if (entityTag.contains("CustomName")) {
                String s = entityTag.getString("CustomName");

                try {
                    ITextComponent customName = ITextComponent.Serializer.fromJson(entityTag.getString("CustomName")).withStyle(TextFormatting.BLUE, TextFormatting.ITALIC);
                    //customName.withStyle(TextFormatting.BLUE, TextFormatting.ITALIC);
                    tooltip.add(new TranslationTextComponent("tooltip.peleball.stored_custom_name", customName, new TranslationTextComponent(entityName).withStyle(TextFormatting.AQUA)));
                } catch (Exception exception) {
                    Main.LOGGER.warn("Failed to parse entity custom name {}", s, exception);
                    tooltip.add(new TranslationTextComponent("tooltip.peleball.stored", new TranslationTextComponent(entityName).withStyle(TextFormatting.AQUA)));
                }
            } else {
                tooltip.add(new TranslationTextComponent("tooltip.peleball.stored", new TranslationTextComponent(entityName).withStyle(TextFormatting.AQUA)));
            }
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.peleball.empty").withStyle(TextFormatting.GRAY));
        }
    }

    public CompoundNBT getEntityCompound(ItemStack stack) {
        return NBTHelper.hasTag(stack, "StoredEntity") ? NBTHelper.getTag(stack, "StoredEntity") : null;
    }
}


