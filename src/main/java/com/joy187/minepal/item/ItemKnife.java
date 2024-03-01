package com.joy187.minepal.item;

import com.joy187.minepal.entity.Pal;
import net.minecraft.item.IItemTier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.world.World;

public class ItemKnife extends SwordItem {

    public ItemKnife(IItemTier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(target instanceof Pal){
            target.setHealth(0);
            return true;
        } else{
            return super.hurtEnemy(stack, target, attacker);
        }
    }

}
