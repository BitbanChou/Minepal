package com.joy187.minepal;


import com.joy187.minepal.container.BookScreen;
import com.joy187.minepal.entity.render.*;
import com.joy187.minepal.init.BlockEntityInit;
import com.joy187.minepal.init.BlockInit;
import com.joy187.minepal.init.EntityInit;
import com.joy187.minepal.init.ParticleInit;
import com.joy187.minepal.particles.SparkParticle;
import com.joy187.minepal.particles.SparkPurpleParticle;
import com.joy187.minepal.particles.SparkRedParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.*;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEventSubscriber extends ModEventSubscriber{

    @Override
    public void init() {
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientModEventSubscriber::setupParticles);
//        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientModEventSubscriber::onKeyRegister);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientModEventSubscriber::onRegisterLayers);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientModEventSubscriber::registerRenderers);

    }

    @Override
    public PlayerEntity getClientSidePlayer() {
        return Minecraft.getInstance().player;
    }



    @SubscribeEvent
    public static void onRegisterRenderer(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.YOUYOU.get(), RenderYouYou::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.CATTIVA.get(), RenderCattiva::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.CHIKIPI.get(), RenderChikipi::new);
        //RenderingRegistry.registerEntityRenderingHandler(EntityInit.PALBALL_ENTITY.get(), RenderPalball::new);
        //RenderingRegistry.registerEntityRenderingHandler(EntityInit.BULLET.get(), RenderBullet::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.EIKTHYRDEER.get(), RenderEikthyrdeer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.FOXPARKS.get(), RenderFoxparks::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.VIXY.get(), RenderVixy::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.TANZEE.get(), RenderTanzee::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.JOLTHOG.get(), RenderJolthog::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.SWEE.get(), RenderSwee::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.DEPRESSO.get(), RenderDepresso::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.MOZZARINA.get(), RenderMozzarina::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.RAYHOUND.get(), RenderRayhound::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.PENGULLET.get(), RenderPengullet::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.GUMOSS.get(), RenderGumoss::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.PETALLIA.get(), RenderPetallia::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.CHILLET.get(), RenderChillet::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.PALBALL_ENTITY.get(),
                renderManager -> new SpriteRenderer(renderManager, Minecraft.getInstance().getItemRenderer()));

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.BULLET.get(),
                renderManager -> new SpriteRenderer(renderManager, Minecraft.getInstance().getItemRenderer()));
        RenderTypeLookup.setRenderLayer(BlockInit.INCUBATOR_BLOCK.get(), RenderType.cutout());

//        ClientRegistry.bindTileEntityRenderer(BlockEntityInit.INCUBATOR_BLOCK_ENTITY.get(), Inc::new);
//        ClientRegistry.bindTileEntityRenderer(BlockEntityInit.STOVE.get(), StoveRenderer::new);
    }
    @OnlyIn(Dist.CLIENT)
    public void openBookGui(ItemStack book) {
        Minecraft.getInstance().setScreen(new BookScreen(book));
    }

}
