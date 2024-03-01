package com.joy187.minepal.item.book;

import com.google.common.base.Strings;
import com.joy187.minepal.Main;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ActionResultType;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraftforge.fml.common.thread.EffectiveSide;
import net.minecraftforge.fml.loading.FMLEnvironment;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class ItemBook extends Item {

    public ItemBook(Properties properties)
    {
        super(properties);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context)
    {
        return showBook(context.getLevel(), context.getItemInHand()).getResult();
    }


    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand)
    {
        //System.out.println("OPEN");
        ItemStack stack = playerIn.getItemInHand(hand);
        return showBook(worldIn, stack);
    }

    private ActionResult<ItemStack> showBook(World worldIn, ItemStack stack)
    {
        if (!worldIn.isClientSide)
            return ActionResult.success(stack);

        CompoundNBT nbt = stack.getTag();
//        if (nbt == null || !nbt.contains("Book", Tag.TAG_STRING))
//            return ActionResult.fail(stack);

        //Minecraft.getInstance().setScreen(new GuidebookScreen(new ResourceLocation(Main.MOD_ID, "textures/gui/re8storybookicon.png")));
//        if (FMLEnvironment.dist == Dist.CLIENT)        
//        	Minecraft.getInstance().setScreen(new BookBase(stack));
//            ClientAPI.displayBook("");
        if(worldIn.isClientSide)
        {
            Main.PROXY.openBookGui(stack);
        }
        return ActionResult.success(stack);
    }

    public ItemStack of(ResourceLocation book)
    {
        ItemStack stack = new ItemStack(this);
        CompoundNBT tag = new CompoundNBT();
        tag.putString("Book", book.toString());
        stack.setTag(tag);
        return stack;
    }

    @Nullable
    public String getBookLocation(ItemStack stack)
    {
        CompoundNBT tag = stack.getTag();
        if (tag != null)
        {
            return tag.getString("Book");
        }
        return null;
    }


    @Override
    public ITextComponent getName(ItemStack stack)
    {
        String book = getBookLocation(stack);
        if (!Strings.isNullOrEmpty(book))
        {
            if (FMLEnvironment.dist == Dist.CLIENT && EffectiveSide.get().isClient())
                return ITextComponent.nullToEmpty("");
        }

        return super.getName(stack);
    }

    public static String getSubtype(ItemStack stack)
    {
        if (stack.getItem() instanceof ItemBook)
        {
            String bookLocation = ((ItemBook) stack.getItem()).getBookLocation(stack);
            return bookLocation == null ? "" : bookLocation;
        }
        return "";
    }

//    @Override
//    public void initializeClient(Consumer<IClientItemExtensions> consumer)
//    {
//        consumer.accept(new IClientItemExtensions()
//        {
//            private final NonNullLazy<BlockEntityWithoutLevelRenderer> ister = NonNullLazy.of(() ->
//                    new BookItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels()));
//
////            @Override
////            public BlockEntityWithoutLevelRenderer getItemStackRenderer()
////            {
////                return ister.get();
////            }
//        });
//    }
    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.book"));
        }else tooltip.add(new TranslationTextComponent("tooltip.shift"));
    }

}

