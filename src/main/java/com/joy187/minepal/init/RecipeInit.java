package com.joy187.minepal.init;
//
//import com.joy187.minepal.Main;
//import com.joy187.minepal.block.blockentity.IncubatorRecipe;
//import net.minecraft.item.crafting.RecipeSerializer;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//
//public class RecipeInit {
//    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
//            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Main.MOD_ID);
//
//    public static final RegistryObject<RecipeSerializer<IncubatorRecipe>> INCUBATOR_SERIALIZER =
//            SERIALIZERS.register("incubator", () -> IncubatorRecipe.Serializer.INSTANCE);
//
//    public static void register(IEventBus eventBus) {
//        SERIALIZERS.register(eventBus);
//    }
//
//}
