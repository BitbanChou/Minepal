package com.joy187.minepal;

import com.joy187.minepal.container.IncubatorScreen;
import com.joy187.minepal.container.PalScreen;
import com.joy187.minepal.init.*;
import com.joy187.minepal.packet.ModMessage;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.ScreenManager;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MOD_ID)
public class Main
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "minepal"; //修改为你的模组名称

    // Directly reference a slf4j logger
    //private static final Logger LOGGER = LogUtils.getLogger();
    public static final Logger LOGGER = LogManager.getLogger(Main.MOD_ID);

    public static ModEventSubscriber PROXY = DistExecutor.runForDist(() -> ClientModEventSubscriber::new, () -> ModEventSubscriber::new);

    public static final ItemGroup TUTORIAL_TAB = new ITEM_TAB("minepal");

    public static class ITEM_TAB extends ItemGroup {
        public ITEM_TAB(String name){
            super(name);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemInit.PALBALL.get());
        }
    }

    public Main()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the commonSetup method for modloading
        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::commonSetup);

        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        EntityInit.ENTITY_TYPES.register(bus);
        //ParticleInit.PARTICLE.register(bus);

        MenuInit.register(bus);
        BlockEntityInit.BLOCK_ENTITIES.register(bus);
        //RecipeInit.register(bus);

        SoundInit.register(bus);
//        ParticleInit.PARTICLE.register(bus);
//        EnchantmentInit.ENCHANT.register(bus);



        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
//            SpawnPlacements.register(EntityInit.YOUYOU.get(), SpawnPlacements.Type.ON_GROUND,
//                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//
//            SpawnPlacements.register(EntityInit.CATTIVA.get(), SpawnPlacements.Type.ON_GROUND,
//                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//
//            SpawnPlacements.register(EntityInit.CHIKIPI.get(), SpawnPlacements.Type.ON_GROUND,
//                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//
//            SpawnPlacements.register(EntityInit.EIKTHYRDEER.get(), SpawnPlacements.Type.ON_GROUND,
//                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//
//            SpawnPlacements.register(EntityInit.FOXPARKS.get(), SpawnPlacements.Type.ON_GROUND,
//                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//
//            SpawnPlacements.register(EntityInit.VIXY.get(), SpawnPlacements.Type.ON_GROUND,
//                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//
//            SpawnPlacements.register(EntityInit.TANZEE.get(), SpawnPlacements.Type.ON_GROUND,
//                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//
//            SpawnPlacements.register(EntityInit.JOLTHOG.get(), SpawnPlacements.Type.ON_GROUND,
//                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//
//            SpawnPlacements.register(EntityInit.SWEE.get(), SpawnPlacements.Type.ON_GROUND,
//                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//
//            SpawnPlacements.register(EntityInit.DEPRESSO.get(), SpawnPlacements.Type.ON_GROUND,
//                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//
//            SpawnPlacements.register(EntityInit.MOZZARINA.get(), SpawnPlacements.Type.ON_GROUND,
//                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//
//            SpawnPlacements.register(EntityInit.RAYHOUND.get(), SpawnPlacements.Type.ON_GROUND,
//                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//
//            SpawnPlacements.register(EntityInit.PENGULLET.get(), SpawnPlacements.Type.ON_GROUND,
//                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//
//            SpawnPlacements.register(EntityInit.GUMOSS.get(), SpawnPlacements.Type.ON_GROUND,
//                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//
//            SpawnPlacements.register(EntityInit.PETALLIA.get(), SpawnPlacements.Type.ON_GROUND,
//                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//
//            SpawnPlacements.register(EntityInit.CHILLET.get(), SpawnPlacements.Type.ON_GROUND,
//                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
       });
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
        ModMessage.register();

    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(BlockInit.PAL_ORE_CLUSTER.get(), RenderType.cutout());
            ScreenManager.register(MenuInit.PAL_CONTAINER_MENU.get(), PalScreen::new);
            ScreenManager.register(MenuInit.INCUBATOR_MENU.get(), IncubatorScreen::new);
            PROXY.clientInit();
        });
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
