package com.joy187.minepal.config;


import java.util.Arrays;
import java.util.List;

public final class ModConfigs {
//
//    public static final ForgeConfigSpec clientConfig;
//    public static final ModConfigs.Client CLIENT;
//    public static final ForgeConfigSpec commonSpec;
//    public static final ModConfigs.Common COMMON;
//
//    static {
//        final Pair<Client, ForgeConfigSpec> clientConfigPair = new ForgeConfigSpec.Builder().configure(Client..new);
//        clientConfig = clientConfigPair.getRight();
//        CLIENT = clientConfigPair.getLeft();
//
//        Pair<Common, ForgeConfigSpec> commonSpecPair = (new ForgeConfigSpec.Builder()).configure(Common..new);
//        commonSpec = commonSpecPair.getRight();
//        COMMON = commonSpecPair.getLeft();
//
//    }
//
//    public static class Client {
//        public static ForgeConfigSpec.BooleanValue enableGunEnchantmentGlint;
//
//        public Client(ForgeConfigSpec.Builder builder) {
//            builder.push("client");
//            {
//                enableGunEnchantmentGlint = builder
//                        .comment("If true, renders enchanted guns from Additional Guns with the enchantment glint. True by default.")
//                        .translation("config.re8gun.client.enable_gun_enchantment_glint")
//                        .define("enableGunEnchantmentGlint", true);
//            }
//            builder.pop();
//        }
//    }
//    public static class Common {
//        public final ModConfigs.Grenades grenade;
//
//        public Common(ForgeConfigSpec.Builder builder) {
//            builder.push("common");
//            this.grenade = new ModConfigs.Grenades(builder);
//            builder.pop();
//        }
//    }
//
//    public static class Grenades {
//        public final ForgeConfigSpec.DoubleValue explosionRadius;
//
//        public Grenades(ForgeConfigSpec.Builder builder) {
//            builder.comment("Properties relating to grenades").push("grenade");
//            this.explosionRadius = builder.comment("The explosion radius of grenade.").defineInRange("explosionRadius", 4.5D, 0.0D, 2.0D);
//            builder.pop();
//        }
//    }
//
//    @Config
    public static List<GunStack> gunStacks = Arrays.asList(
        new GunStack("minecraft.dragon_egg", 5),
            new GunStack("minecraft.egg", 3),
            new GunStack("re8gun.m1851", 0),
        new GunStack("re8gun.v61", 0),
        new GunStack("re8gun.lemi", 0),
        new GunStack("re8gun.m1911", 0),
        new GunStack("re8gun.usmai", 0),
        new GunStack("re8gun.m1897", 3),
        new GunStack("re8gun.stg", 3),
        new GunStack("re8gun.striker", 3),
        new GunStack("re8gun.stake", 6),
        new GunStack("re8gun.samurai", 6),
        new GunStack("re8gun.handcannon", 6),
        new GunStack("re8gun.w870", 3),
        new GunStack("re8gun.syg12", 3),
        new GunStack("re8gun.gm79", 5),
        new GunStack("re8gun.f2rifle", 4),
        new GunStack("re8gun.sa110", 4),
        new GunStack("re8gun.rifle", 1),
        new GunStack("re8gun.wcx", 1),
        new GunStack("re8gun.dragoon", 2),
        new GunStack("re8gun.tmp", 2),
        new GunStack("re8gun.chicago", 2),
        new GunStack("re8gun.red9", 0),
        new GunStack("re8gun.rpg", 5),
        new GunStack("re8gun.mp5", 2),
        new GunStack("re8gun.m4", 1),
        new GunStack("re8gun.stinggray", 4),
        new GunStack("cgm.pistol", 0),
        new GunStack("cgm.shotgun",3),
        new GunStack("cgm.sniper_rifle",4),
        new GunStack("cgm.grenade_launcher",5),
        new GunStack("cgm.bazooka",5),
        new GunStack("cgm.mini_gun",7),
        new GunStack("cgm.rifle",1),
        new GunStack("cgm.assault_rifle",1),
        new GunStack("cgm.machine_pistol",6),
        new GunStack("cgm.heavy_rifle",1));

//    public static List<ItemStack> getTestItems() {
//        if(testItems == null)
//            computeTestItems();
//        return testItems;
//    }
//
//    private static void computeTestItems() {
//        testItems = gunStacks.stream()
//                .map(ResourceLocation..new)
//                .map(BuiltinRegistries.ITEM..get)
//                .filter(i -> i != Items.AIR)
//                .map(ItemStack..new)
//                .toList();
//    }

}


