package com.joy187.minepal;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber
{
    public void init() {
    }

    public PlayerEntity getClientSidePlayer() {
        return null;
    }

    public void clientInit() {

    }

    public void openBookGui(ItemStack book){

    }

}