package com.joy187.minepal.item;

import com.google.common.base.Predicate;
import com.joy187.minepal.config.ModConfigs;
import com.joy187.minepal.entity.Pal;
import net.minecraft.client.audio.SoundSource;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.stats.Stats;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class ItemDetector extends Item {
    public static final int USE_DURATION = 650;
    public static final float ZOOM_FOV_MODIFIER = 0.3F;

    private static final Predicate<Entity> BIRDWATCHER_AREA = (entity) -> {
        return entity.isAlive() && !(entity instanceof PlayerEntity);
    };

    public ItemDetector(Item.Properties p_151205_) {
        super(p_151205_);
    }

    public int getUseDuration(ItemStack p_151222_) {
        return USE_DURATION;
    }

    public UseAction getUseAnimation(ItemStack p_151224_) {
        return UseAction.BLOCK;
    }

    public ActionResult<ItemStack> use(World p_151218_, PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);
        if(!playerIn.getCooldowns().isOnCooldown(this))
        {
//            if(playerIn.getEffect(Effects.NIGHT_VISION)==null)
//                playerIn.addEffect(new EffectInstance(Effects.NIGHT_VISION, 320, 0));
            playerIn.level.playSound(playerIn, playerIn.blockPosition(), SoundEvents.LEVER_CLICK, SoundCategory.PLAYERS, 1.5F, 0.8F / (playerIn.level.getRandom().nextFloat() * 0.4F + 0.8F));
            for (LivingEntity livingentity : playerIn.level.getEntitiesOfClass(LivingEntity.class, playerIn.getBoundingBox().inflate(40.0D), BIRDWATCHER_AREA)) {
                if(livingentity.getEffect(Effects.GLOWING)==null && (livingentity instanceof Pal))
                    livingentity.addEffect(new EffectInstance(Effects.GLOWING, 360, 0));
            }

            playerIn.playSound(SoundEvents.LEVER_CLICK, 1.0F, 1.0F);
            playerIn.awardStat(Stats.ITEM_USED.get(this));

            playerIn.getCooldowns().addCooldown(this,360);
            playerIn.getMainHandItem().hurtAndBreak(1, playerIn, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
            });
        }
        return startUsingInstantly(p_151218_, playerIn, hand);
    }

    public static ActionResult<ItemStack> startUsingInstantly(World p_150960_, PlayerEntity p_150961_, Hand p_150962_) {
        p_150961_.startUsingItem(p_150962_);
        return ActionResult.consume(p_150961_.getItemInHand(p_150962_));
    }

    public ItemStack finishUsingItem(ItemStack p_151209_, World p_151210_, LivingEntity p_151211_) {
        this.stopUsing(p_151211_);
        return p_151209_;
    }

    public void releaseUsing(ItemStack p_151213_, World p_151214_, LivingEntity p_151215_, int p_151216_) {
        this.stopUsing(p_151215_);
    }

    private void stopUsing(LivingEntity p_151207_) {
        p_151207_.playSound(SoundEvents.LEVER_CLICK, 1.0F, 1.0F);
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.detector"));
        }else tooltip.add(new TranslationTextComponent("tooltip.shift"));
    }


}

