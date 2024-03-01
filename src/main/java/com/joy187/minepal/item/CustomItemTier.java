package com.joy187.minepal.item;

import java.util.function.Supplier;

import com.joy187.minepal.init.ItemInit;
import net.minecraft.item.IItemTier;
import net.minecraft.tags.ItemTags;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public enum CustomItemTier implements IItemTier {

    TOOL_KKUKRI( 5, 1570, 10.0F, 9.0F, 80, () -> {
        return Ingredient.of(Items.GOLD_INGOT);
    }),

    TOOL_XUANFENG(8, 1870, 8.0F, 10.0F, 70, () -> {
        return Ingredient.of(Items.NETHERITE_INGOT);
    }),

    TOOL_HSAW(10, 3500, 8.0F, 49.0F, 270, () -> {
        return Ingredient.of(Items.NETHERITE_INGOT);
    }),

    TOOL_KEY2(10, 3, 10.0F, 9.0F, 40, () -> {
        return Ingredient.of(Items.DIAMOND);
    }),

    TOOL_KEY3(15, 5, 16.0F, 19.0F, 100, () -> {
        return Ingredient.of(Items.NETHERITE_SCRAP);
    }),

    TOOL_HAGWAND(5, 190, 10.0F, 14.0F, 180, () -> {
        return Ingredient.of(Items.DIAMOND);
    }),

    TOOL_FLYPAT(4, 290, 10.0F, 4.0F, 180, () -> {
        return Ingredient.of(Items.REDSTONE);
    }),

    TOOL_ZHIHU(2, 370, 8.0F, 4.0F, 30, () -> {
        return Ingredient.of(Items.IRON_INGOT);
    }),
	
    TOOL_SILIVERBLADE(5, 70, 8.0F, 12.0F, 100, () -> {
        return Ingredient.of(Items.NETHERITE_INGOT);
    }),
	
    TOOL_DIMIHAND(8, 1670, 8.0F, 16.0F, 100, () -> {
        return Ingredient.of(Items.NETHERITE_INGOT);
    }),

    TOOL_KEY(5, 1, 8.0F, 9.0F, 20, () -> {
        return Ingredient.of(Items.NETHERITE_INGOT);
    });


	   private final int level;
	   private final int uses;
	   private final float speed;
	   private final float damage;
	   private final int enchantmentValue;
	   private final Ingredient repairIngredient;

	   private CustomItemTier(int p_43332_, int p_43333_, float p_43334_, float p_43335_, int p_43336_, Supplier<Ingredient> p_43337_) {
	      this.level = p_43332_;
	      this.uses = p_43333_;
	      this.speed = p_43334_;
	      this.damage = p_43335_;
	      this.enchantmentValue = p_43336_;
	      this.repairIngredient = p_43337_.get();
	   }

	   public int getUses() {
	      return this.uses;
	   }

	   public float getSpeed() {
	      return this.speed;
	   }

	   public float getAttackDamageBonus() {
	      return this.damage;
	   }

	   public int getLevel() {
	      return this.level;
	   }

	   public int getEnchantmentValue() {
	      return this.enchantmentValue;
	   }

	   public Ingredient getRepairIngredient() {
	      return this.repairIngredient;
	   }

//	   @javax.annotation.Nullable public net.minecraft.tags.Tag<net.minecraft.world.level.block.Block> getTag() { return net.minecraftforge.common.ForgeHooks.getTagFromVanillaTier(this); }
}
