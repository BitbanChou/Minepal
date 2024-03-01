package com.joy187.minepal.util;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public final class RecipeUtils {
    private RecipeUtils() {
    }

//    @Nullable
//    public static RecipeManager getRecipeManager(@Nullable Level world){
//        RecipeManager manager = DistExecutor.safeCallWhenOn(Dist.CLIENT, ()->ClientUtils::getRecipeManager);
//        return manager != null ?  manager : world != null ? world.getRecipeManager() : null;
//    }

//    @Nullable
//    public static <C extends Container, T extends Recipe<C>> Recipe<C> getRecipe(RecipeType<T> recipeType, ResourceLocation name, @Nullable Level world) {
//        RecipeManager manager = getRecipeManager(world);
//        if(manager == null){
//            return null;
//        }
//        return manager.byType(recipeType).get(name);
//    }
//
//    public static <C extends Container, T extends Recipe<C>> List<T> getRecipes(RecipeType<T> recipeType, C inventory, @Nullable Level world) {
//        RecipeManager manager = getRecipeManager(world);
//        if (manager == null || world == null) {
//            return Collections.emptyList();
//        }
//        return manager.getRecipesFor(recipeType, inventory, world);
//    }

    public static List<ICraftingRecipe> findMatchingRecipes(CraftingInventory inventory, World world) {
        RecipeManager recipeManager = world.getRecipeManager();
        return recipeManager.getRecipesFor(IRecipeType.CRAFTING, inventory, world).stream()
                .filter(recipe -> recipe.matches(inventory, world))
                .collect(Collectors.toList());
    }

}
