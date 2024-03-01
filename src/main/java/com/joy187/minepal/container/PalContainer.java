package com.joy187.minepal.container;


import com.joy187.minepal.Main;
import com.joy187.minepal.entity.Pal;
import com.joy187.minepal.util.PalUtils;
import net.minecraft.block.ChestBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeContainerType;

import javax.annotation.Nullable;

public class PalContainer extends Container {
    public static final ResourceLocation EMPTY_ARMOR_SLOT_TOOL = new ResourceLocation(Main.MOD_ID,"textures/item/empty_armor_slot_tool.png");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_FOOD = new ResourceLocation(Main.MOD_ID,"textures/item/empty_armor_slot_food.png");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_CHESTPLATE = new ResourceLocation(Main.MOD_ID,"textures/item/empty_armor_slot_chestplate");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_LEGGINGS = new ResourceLocation(Main.MOD_ID,"textures/item/empty_armor_slot_leggings.png");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_BOOTS = new ResourceLocation(Main.MOD_ID,"textures/item/empty_armor_slot_boots.png");
    static final ResourceLocation[] TEXTURE_EMPTY_SLOTS = new ResourceLocation[]{EMPTY_ARMOR_SLOT_TOOL, EMPTY_ARMOR_SLOT_FOOD, EMPTY_ARMOR_SLOT_FOOD, EMPTY_ARMOR_SLOT_FOOD, EMPTY_ARMOR_SLOT_FOOD,EMPTY_ARMOR_SLOT_HELMET, EMPTY_ARMOR_SLOT_CHESTPLATE, EMPTY_ARMOR_SLOT_LEGGINGS, EMPTY_ARMOR_SLOT_BOOTS};
    private static final EquipmentSlotType[] SLOT_IDS = new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};

//    public PalContainer(int windowId, Inventory playerInventory) {
//        this(windowId,playerInventory, EntityInit.YOUYOU.get().create(playerInventory.player.level));
//    }
//
    public static final ContainerType<PalContainer> TYPE = IForgeContainerType.create((windowId, inv, data) -> new PalContainer(windowId, inv, data.readInt()));
    protected Pal pal;

//    public PalContainer(int id, Inventory inventory, int entityId) {
//        super(TYPE, id);
//        this.pal = (Pal) inventory.player.level.getEntity(entityId);
//    }

    public PalContainer(int windowId, PlayerInventory playerInventory, int id) {
        //super(MenuInit.PAL_CONTAINER_MENU.get(), windowId);
        super(TYPE, windowId);
        this.pal = (Pal) playerInventory.player.level.getEntity(id);

        Entity customCreatureEntity=playerInventory.player.level.getEntity(id);
        if(customCreatureEntity instanceof Pal)
        {
            // 添加生物背包的物品槽
            for (int row = 0; row < 4; ++row) { // 将行数改为4

                if(row == 0){
                    for (int col = 0; col < 9; ++col) {
                        if(col==0){
                            this.addSlot(new Slot(((Pal)customCreatureEntity).getInventory(), col + row * 9, 8 + col * 18, 1 * 18 + row * 18) {
//                                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
//                                    return Pair.of(EMPTY_ARMOR_SLOT_TOOL, EMPTY_ARMOR_SLOT_TOOL);
//                                }
                            });
                        }
                        else if(col>0 && col<=4)
                        {
                            this.addSlot(new Slot(((Pal)customCreatureEntity).getInventory(), col + row * 9, 8 + col * 18, 1 * 18 + row * 18) {
                                public int getMaxStackSize() {
                                    return 64;
                                }

                                public boolean mayPlace(ItemStack p_39746_) {
                                    return p_39746_.isEdible();
                                }

                                public boolean mayPickup(PlayerEntity p_39744_) {
                                    ItemStack itemstack = this.getItem();
                                    return !itemstack.isEmpty() && !p_39744_.isCreative() && EnchantmentHelper.hasBindingCurse(itemstack) ? false : super.mayPickup(p_39744_);
                                }

//                                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
//                                    return Pair.of(EMPTY_ARMOR_SLOT_FOOD, EMPTY_ARMOR_SLOT_FOOD);
//                                }
                            });
                        }
                        if(col>4)
                        {
                            final EquipmentSlotType equipmentslot = SLOT_IDS[col-5];
                            this.addSlot(new Slot(((Pal)customCreatureEntity).getInventory(), col + row * 9, 8 + col * 18, 1 * 18 + row * 18) {
                                public void set(ItemStack p_219985_) {
                                    ItemStack itemstack = this.getItem();
                                    super.set(p_219985_);
                                    //((Pal)customCreatureEntity).getInventory().setItem(col, itemstack);
                                    //((Pal)customCreatureEntity).getInventory().setItem(this.getInventory.selected,p_219985_ );
                                    PalUtils.onEquipItem(equipmentslot, itemstack, p_219985_);

                                }

                                public int getMaxStackSize() {
                                    return 1;
                                }

                                public boolean mayPlace(ItemStack p_39746_) {
                                    return p_39746_.canEquip(equipmentslot, customCreatureEntity);
                                }

                                public boolean mayPickup(PlayerEntity p_39744_) {
                                    ItemStack itemstack = this.getItem();
                                    return !itemstack.isEmpty() && !p_39744_.isCreative() && EnchantmentHelper.hasBindingCurse(itemstack) ? false : super.mayPickup(p_39744_);
                                }

//                                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
//                                    return Pair.of(TEXTURE_EMPTY_SLOTS[equipmentslot.getIndex()], TEXTURE_EMPTY_SLOTS[equipmentslot.getIndex()]);
//                                }
                            });
                        }

                    }
                }
                else{
                    for (int col = 0; col < 9; ++col) {
                        this.addSlot(new Slot(((Pal)customCreatureEntity).getInventory(), col + row * 9, 8 + col * 18, 1 * 18 + row * 18));
                    }
                }
            }

            // 添加玩家背包的物品槽
            for (int row = 0; row < 3; ++row) {
                for (int col = 0; col < 9; ++col) {
                    this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 5 * 18 + 12 + row * 18));
                }
            }

            // 添加玩家快捷栏的物品槽
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 8 * 18 + 16));
            }

        }

    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        // 检查容器是否仍然有效
        return pal.isAlive() && pal.distanceTo(playerIn) < 10.0F;
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        // 实现物品快速移动的逻辑
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            itemstack = slotStack.copy();

            // 自定义逻辑...
            if (index < 36) { // 修改条件
                // 从生物背包中移动物品到玩家背包
                if (!this.moveItemStackTo(slotStack, 36, 45, false)) { // 修改范围
                    return ItemStack.EMPTY;
                }
            } else {
                // 从玩家背包中移动物品到生物背包
                if (!this.moveItemStackTo(slotStack, 0, 36, false)) { // 修改范围
                    return ItemStack.EMPTY;
                }
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    public static INamedContainerProvider create(int entityId) {
        return new INamedContainerProvider() {

            @Override
            public ITextComponent getDisplayName() {
                return ITextComponent.nullToEmpty("Pal");
            }

            @Nullable
            @Override
            public Container createMenu(int index, PlayerInventory playerInventory, PlayerEntity player) {
                return new PalContainer(index, playerInventory, entityId);
            }
        };
    }


}

