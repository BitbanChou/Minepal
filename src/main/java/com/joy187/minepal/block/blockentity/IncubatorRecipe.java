//package com.joy187.minepal.block.blockentity;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.joy187.minepal.Main;
//import net.minecraft.core.NonNullList;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.GsonHelper;
//import net.minecraft.inventory.Inventory;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.crafting.*;
//import net.minecraft.world.World;
//
//public class IncubatorRecipe implements Recipe<Inventory> {
//    private final ResourceLocation id;
//    private final ItemStack output;
//    private final NonNullList<Ingredient> recipeItems;
//
//    public IncubatorRecipe(ResourceLocation id, ItemStack output,
//                                NonNullList<Ingredient> recipeItems) {
//        this.id = id;
//        this.output = output;
//        this.recipeItems = recipeItems;
//    }
//
//    @Override
//    public boolean matches(PlayerInventory pContainer, Level pLevel) {
//        return (recipeItems.get(0).test(pContainer.getItem(1))
//                && recipeItems.get(1).test(pContainer.getItem(0)))
//                || (recipeItems.get(0).test(pContainer.getItem(0))
//                && recipeItems.get(1).test(pContainer.getItem(1)));
//    }
//
//    @Override
//    public NonNullList<Ingredient> getIngredients() {
//        return recipeItems;
//    }
//
//    @Override
//    public ItemStack assemble(PlayerInventory pContainer) {
//        return output;
//    }
//
//    @Override
//    public boolean canCraftInDimensions(int pWidth, int pHeight) {
//        return true;
//    }
//
//    @Override
//    public ItemStack getResultItem() {
//        return output.copy();
//    }
//
//    @Override
//    public ResourceLocation getId() {
//        return id;
//    }
//
//    @Override
//    public RecipeSerializer<?> getSerializer() {
//        return Serializer.INSTANCE;
//    }
//
//    @Override
//    public RecipeType<?> getType() {
//        return Type.INSTANCE;
//    }
//
//    public static class Type implements RecipeType<IncubatorRecipe> {
//        private Type() { }
//        public static final Type INSTANCE = new Type();
//        public static final String ID = "incubator";
//    }
//
//    public static class Serializer implements RecipeSerializer<IncubatorRecipe> {
//        public static final Serializer INSTANCE = new Serializer();
//        public static final ResourceLocation ID = new ResourceLocation(Main.MOD_ID,"incubator");
//
//        @Override
//        public IncubatorRecipe fromJson(ResourceLocation id, JsonObject json) {
//            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
//
//            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
//            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
//
//            for (int i = 0; i < inputs.size(); i++) {
//                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
//            }
//
//            return new IncubatorRecipe(id, output, inputs);
//        }
//
//        @Override
//        public IncubatorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
//            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
//
//            for (int i = 0; i < inputs.size(); i++) {
//                inputs.set(i, Ingredient.fromNetwork(buf));
//            }
//
//            ItemStack output = buf.readItem();
//            return new IncubatorRecipe(id, output, inputs);
//        }
//
//        @Override
//        public void toNetwork(FriendlyByteBuf buf, IncubatorRecipe recipe) {
//            buf.writeInt(recipe.getIngredients().size());
//            for (Ingredient ing : recipe.getIngredients()) {
//                ing.toNetwork(buf);
//            }
//            buf.writeItemStack(recipe.getResultItem(), false);
//        }
//
//        @SuppressWarnings("unchecked") // Need this wrapper, because generics
//        private static <G> Class<G> castClass(Class<?> cls) {
//            return (Class<G>)cls;
//        }
//    }
//
//
//
//}
