package com.joy187.minepal.util;

import com.joy187.minepal.entity.*;
import com.joy187.minepal.init.EntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.world.World;
import org.lwjgl.system.CallbackI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PalUtils {

//    private static final Map<String, EntityType<? extends Pal> > ENTITY_MAP = new HashMap<>();
//
//    static {
//        // 注册生物类型和对应的类和初始化方法
//        ENTITY_MAP.put("sheep", EntityInit.YOUYOU.get());
//        ENTITY_MAP.put("cat", EntityInit.CATTIVA.get());
//        ENTITY_MAP.put("chicken", (level, pos) -> new Chikipi(EntityInit.CHIKIPI.get(), level));
//        ENTITY_MAP.put("tanzee-youyou", (level, pos) -> new Jolthog(EntityInit.JOLTHOG.get(), level));
//        ENTITY_MAP.put("swee-youyou", (level, pos) -> new Depresso(EntityInit.DEPRESSO.get(), level));
//    }

    public static final Map<String, String> OCCUPATION = new HashMap<>();

    static {
        OCCUPATION.put("shear","✂");
        OCCUPATION.put("pickaxe","⛏");
        OCCUPATION.put("axe","🔨");
        OCCUPATION.put("hoe","🦯");
        OCCUPATION.put("harvest","🦯");
        OCCUPATION.put("milk","🪣");

    }

    public static String compareString(String entity1, String entity2)
    {
        String combinedString="";
        String[] parts = entity1.split(":");
        String[] parts2 = entity2.split(":");
        if(parts.length>1 && parts2.length>1)
        {
            entity1 = parts[1];
            entity2 = parts2[1];

            // 比较两个字符串的大小
            int comparisonResult = entity1.compareTo(entity2);

            // 根据比较结果拼接字符串
            if (comparisonResult < 0) {
                // entity1 小于 entity2
                combinedString = entity1 + "-" + entity2;
            } else if (comparisonResult > 0) {
                // entity1 大于 entity2
                combinedString = entity2 + "-" + entity1;
            } else {
                // entity1 等于 entity2
                combinedString = entity1 + "-" + entity2;
            }

        }

        return combinedString;
    }

    public static String containString(String entity1, String entity2)
    {
        String combinedString="x-x";
        String[] parts = entity1.split(":");
        String[] parts2 = entity2.split(":");
        if(parts.length>1 && parts2.length>1)
        {
            entity1 = parts[1];
            entity2 = parts2[1];

            if(entity1.contains("squid") || entity2.contains("deer"))
                combinedString="deer-x";

        }

        return combinedString;
    }

    public static List<ICraftingRecipe> findMatchingRecipes(CraftingInventory inventory, World world) {
        RecipeManager recipeManager = world.getRecipeManager();
        return recipeManager.getRecipesFor(IRecipeType.CRAFTING, inventory, world).stream()
                .filter(recipe -> recipe.matches(inventory, world))
                .collect(Collectors.toList());
    }

    public static BlockPos getOnPos(Pal pal) {
        int i = MathHelper.floor(pal.position().x);
        int j = MathHelper.floor(pal.position().y - (double)0.2F);
        int k = MathHelper.floor(pal.position().z);
        BlockPos blockpos = new BlockPos(i, j, k);
        if (pal.level.isEmptyBlock(blockpos)) {
            BlockPos blockpos1 = blockpos.below();
            BlockState blockstate = pal.level.getBlockState(blockpos1);
            if (blockstate.collisionExtendsVertically(pal.level, blockpos1, pal)) {
                return blockpos1;
            }
        }

        return blockpos;
    }

    public static final Predicate<Entity> NO_CREATIVE_OR_SPECTATOR = (p_20436_) -> {
        return !(p_20436_ instanceof PlayerEntity) || !p_20436_.isSpectator() && !((PlayerEntity)p_20436_).isCreative();
    };

    public static void onEquipItem(EquipmentSlotType p_238393_, ItemStack p_238394_, ItemStack p_238395_) {
        boolean flag = p_238395_.isEmpty() && p_238394_.isEmpty();
        if (!flag && !ItemStack.isSameIgnoreDurability(p_238394_, p_238395_)) {


        }
    }

}
