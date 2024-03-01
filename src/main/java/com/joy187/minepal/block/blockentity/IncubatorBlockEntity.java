package com.joy187.minepal.block.blockentity;

import com.joy187.minepal.block.IncubatorBlock;
import com.joy187.minepal.container.IncubatorMenu;
import com.joy187.minepal.entity.*;
import com.joy187.minepal.init.BlockEntityInit;
import com.joy187.minepal.init.EntityInit;
import com.joy187.minepal.item.ItemPalball;
import com.joy187.minepal.util.NBTHelper;
import com.joy187.minepal.util.PalUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.monster.StrayEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.ZombieVillagerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.inventory.Inventory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class IncubatorBlockEntity extends LockableTileEntity implements INamedContainerProvider, ITickableTileEntity {

    private BlockState blockState;
    protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);

    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final IIntArray data;
    private int progress = 0;
    private int maxProgress = 60;

    public IncubatorBlockEntity() {
        super(BlockEntityInit.INCUBATOR_BLOCK_ENTITY.get());
        this.data = new IIntArray() {
            public int get(int index) {
                switch (index) {
                    case 0: return IncubatorBlockEntity.this.progress;
                    case 1: return IncubatorBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: IncubatorBlockEntity.this.progress = value; break;
                    case 1: IncubatorBlockEntity.this.maxProgress = value; break;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int p_70301_1_) {
        return this.items.get(p_70301_1_);
    }

    @Override
    public ItemStack removeItem(int p_70298_1_, int p_70298_2_) {
        return ItemStackHelper.removeItem(this.items, p_70298_1_, p_70298_2_);
    }

    @Override
    public ItemStack removeItemNoUpdate(int p_70304_1_) {
        return ItemStackHelper.takeItem(this.items, p_70304_1_);
    }


    @Override
    public void setItem(int p_70299_1_, ItemStack p_70299_2_) {
        ItemStack itemstack = this.items.get(p_70299_1_);
        boolean flag = !p_70299_2_.isEmpty() && p_70299_2_.sameItem(itemstack) && ItemStack.tagMatches(p_70299_2_, itemstack);
        this.items.set(p_70299_1_, p_70299_2_);
        if (p_70299_2_.getCount() > this.getMaxStackSize()) {
            p_70299_2_.setCount(this.getMaxStackSize());
        }

        if (p_70299_1_ == 0 && !flag) {
            this.progress = 0;
            this.setChanged();
        }
    }

    @Override
    public boolean stillValid(PlayerEntity p_70300_1_) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return p_70300_1_.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.incubator");
    }

//    @Override
//    protected ITextComponent getDefaultName() {
//        return null;
//    }

    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.incubator");
    }

    @Nullable
    @Override
    public Container createMenu(int pContainerId, PlayerInventory pInventory) {
        return new IncubatorMenu(pContainerId, pInventory, this, this.data);
    }


    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);

        if (this.level != null) {
            PAL_ENTITY_MAP.put("sheep-sheep", () -> new YouYou(EntityInit.YOUYOU.get(),this.level));
            PAL_ENTITY_MAP.put("llama-llama",() -> new YouYou(EntityInit.YOUYOU.get(),this.level));
            PAL_ENTITY_MAP.put("youyou-youyou",() -> new YouYou(EntityInit.YOUYOU.get(),this.level));
            PAL_ENTITY_MAP.put("cattiva-youyou",() -> new YouYou(EntityInit.YOUYOU.get(),this.level));
            PAL_ENTITY_MAP.put("llama-youyou",() -> new YouYou(EntityInit.YOUYOU.get(),this.level));
            PAL_ENTITY_MAP.put("sheep-youyou",() -> new YouYou(EntityInit.YOUYOU.get(),this.level));


            PAL_ENTITY_MAP.put("chicken-chicken",() -> new Chikipi(EntityInit.CHIKIPI.get(),this.level));
            PAL_ENTITY_MAP.put("chikipi-chikipi",() -> new Chikipi(EntityInit.CHIKIPI.get(),this.level));
            PAL_ENTITY_MAP.put("chicken-chikipi",() -> new Chikipi(EntityInit.CHIKIPI.get(),this.level));

            PAL_ENTITY_MAP.put("cat-cat",() -> new Cattiva(EntityInit.CATTIVA.get(),this.level));
            PAL_ENTITY_MAP.put("vixy-youyou",() -> new Cattiva(EntityInit.CATTIVA.get(),this.level));
            PAL_ENTITY_MAP.put("cattiva-cattiva",() -> new Cattiva(EntityInit.CATTIVA.get(),this.level));
            PAL_ENTITY_MAP.put("cattiva-cat",() -> new Cattiva(EntityInit.CATTIVA.get(),this.level));

            PAL_ENTITY_MAP.put("chikipi-swee",() -> new Foxparks(EntityInit.FOXPARKS.get(),this.level));
            PAL_ENTITY_MAP.put("foxparks-foxparks",() -> new Foxparks(EntityInit.FOXPARKS.get(),this.level));

            PAL_ENTITY_MAP.put("cow-cow",() -> new Mozzarina(EntityInit.MOZZARINA.get(),this.level));
            PAL_ENTITY_MAP.put("mozzarina-mozzarina",() -> new Mozzarina(EntityInit.MOZZARINA.get(),this.level));
            PAL_ENTITY_MAP.put("cow-mozzarina",() -> new Mozzarina(EntityInit.MOZZARINA.get(),this.level));

            PAL_ENTITY_MAP.put("tanzee-youyou",() -> new Jolthog(EntityInit.JOLTHOG.get(),this.level));
            PAL_ENTITY_MAP.put("jolthog-jolthog",() -> new Jolthog(EntityInit.JOLTHOG.get(),this.level));
            PAL_ENTITY_MAP.put("depresso-jolthog",() -> new Jolthog(EntityInit.JOLTHOG.get(),this.level));
            PAL_ENTITY_MAP.put("chikipi-gumoss",() -> new Jolthog(EntityInit.JOLTHOG.get(),this.level));

            PAL_ENTITY_MAP.put("swee-youyou",() -> new Depresso(EntityInit.DEPRESSO.get(),this.level));
            PAL_ENTITY_MAP.put("depresso-depresso",() -> new Depresso(EntityInit.DEPRESSO.get(),this.level));

            PAL_ENTITY_MAP.put("skeleton-squid",() -> new WitherSkeletonEntity(EntityType.WITHER_SKELETON,this.level));
            PAL_ENTITY_MAP.put("skeleton-skeleton",() -> new StrayEntity(EntityType.STRAY,this.level));
            PAL_ENTITY_MAP.put("zombie-zombie",() -> new ZombieVillagerEntity(EntityType.ZOMBIE_VILLAGER,this.level));

            PAL_ENTITY_MAP.put("wolf-wolf",() -> new Rayhound(EntityInit.RAYHOUND.get(),this.level));
            PAL_ENTITY_MAP.put("rayhound-rayhound",() -> new Rayhound(EntityInit.RAYHOUND.get(),this.level));
            PAL_ENTITY_MAP.put("rayhound-wolf",() -> new Rayhound(EntityInit.RAYHOUND.get(),this.level));

            PAL_ENTITY_MAP.put("gumoss-gumoss",() -> new Gumoss(EntityInit.GUMOSS.get(),this.level));

            PAL_ENTITY_MAP.put("tanzee-tanzee",() -> new Tanzee(EntityInit.TANZEE.get(),this.level));
            PAL_ENTITY_MAP.put("gumoss-tanzee",() -> new Tanzee(EntityInit.TANZEE.get(),this.level));

            PAL_ENTITY_MAP.put("pengullet-pengullet",() -> new Pengullet(EntityInit.PENGULLET.get(),this.level));
            PAL_ENTITY_MAP.put("cattiva-gumoss",() -> new Pengullet(EntityInit.PENGULLET.get(),this.level));
            PAL_ENTITY_MAP.put("gumoss-youyou",() -> new Pengullet(EntityInit.PENGULLET.get(),this.level));
            PAL_ENTITY_MAP.put("cattiva-tanzee",() -> new Pengullet(EntityInit.PENGULLET.get(),this.level));
            PAL_ENTITY_MAP.put("tanzee-vixy",() -> new Pengullet(EntityInit.PENGULLET.get(),this.level));

            PAL_ENTITY_MAP.put("eikthyrdeer-eikthyrdeer",() -> new Eikthyrdeer(EntityInit.EIKTHYRDEER.get(),this.level));

            PAL_ENTITY_MAP.put("vixy-vixy",() -> new Vixy(EntityInit.VIXY.get(),this.level));
            PAL_ENTITY_MAP.put("chikipi-foxparks",() -> new Vixy(EntityInit.VIXY.get(),this.level));

            PAL_ENTITY_MAP.put("swee-swee",() -> new Swee(EntityInit.SWEE.get(),this.level));
            PAL_ENTITY_MAP.put("gumoss-jolthog",() -> new Swee(EntityInit.SWEE.get(),this.level));
            PAL_ENTITY_MAP.put("pengullet-tanzee",() -> new Swee(EntityInit.SWEE.get(),this.level));

            PAL_ENTITY_MAP.put("petallia-petallia",() -> new Petallia(EntityInit.PETALLIA.get(),this.level));

            PAL_ENTITY_MAP2.put("deer-x",() -> new Eikthyrdeer(EntityInit.EIKTHYRDEER.get(),this.level));

        }

    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

//    @Override
//    public CompoundNBT save(CompoundNBT tag) {
//        tag.put("inventory", itemHandler.serializeNBT());
//        tag.putInt("incubator.progress", progress);
//        super.save(tag);
//        return tag;
//    }
//
//    @Override
//    public void load(BlockState p_230337_1_, CompoundNBT nbt) {
//        super.load(p_230337_1_,nbt);
//        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
//        progress = nbt.getInt("incubator.progress");
//    }
    @Override
    public CompoundNBT save(CompoundNBT tag) {
        tag.put("inventory", this.itemHandler.serializeNBT());
        tag.putInt("incubator.progress", this.progress);
        super.save(tag);
        return tag;
    }

    @Override
    public void load(BlockState p_230337_1_,CompoundNBT nbt) {
        super.load(p_230337_1_,nbt);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.items);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("incubator.progress");
    }

    public void drops() {
        Inventory inventory = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        InventoryHelper.dropContents(this.level, this.worldPosition, inventory);
    }

    public void tick() {
        World pLevel=this.level;
        BlockPos pPos=this.worldPosition;
        BlockState pState=this.getBlockState();

        if(hasRecipe(this)) {
            System.out.println("11111111111");
            //pState.setValue(IncubatorBlock.LIT, Boolean.valueOf(true));
            this.progress++;
            setChanged();
            if(this.progress > this.maxProgress) {
                craftItem(this, pPos);
            }

        } else {
            //pState.setValue(IncubatorBlock.LIT, Boolean.valueOf(false));
            this.resetProgress();
            setChanged();
        }
//    	if(hasRecipe(pBlockEntity) && notReachLimit(pBlockEntity)) {
//    		craftItem(pBlockEntity);
//    	}
    }

    private static boolean hasRecipe(IncubatorBlockEntity entity) {
        World level = entity.level;
        Inventory inventory = new Inventory(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }
        if(inventory.getItem(0).getItem() instanceof ItemPalball &&
                inventory.getItem(1).getItem() instanceof ItemPalball && hasFuelSlot(entity))
        {
            if(NBTHelper.hasTag(inventory.getItem(0), "StoredEntity") &&
                    NBTHelper.hasTag(inventory.getItem(1), "StoredEntity"))
            {
                CompoundNBT entity1Tag = NBTHelper.getTag(inventory.getItem(0), "StoredEntity");
                CompoundNBT entity2Tag = NBTHelper.getTag(inventory.getItem(1), "StoredEntity");

                String entity1 = entity1Tag.getString("id");
                String entity2 = entity2Tag.getString("id");

                String combinedS= PalUtils.compareString(entity1,entity2);
                //System.out.println(PAL_ENTITY_MAP.size());
                if(PAL_ENTITY_MAP.containsKey(combinedS))
                    return true;

                combinedS= PalUtils.containString(entity1,entity2);
                if(PAL_ENTITY_MAP2.containsKey(combinedS))
                    return true;
//            if(entity1.contains("sheep") && entity2.contains("sheep"))
//                return true;
//            if(entity1.contains("cat") && entity2.contains("cat"))
//                return true;
//            if(entity1.contains("chicken") && entity2.contains("chicken"))
//                return true;
//            if((entity1.contains("tanzee") && entity2.contains("youyou")) || (entity2.contains("tanzee") && entity1.contains("youyou")))
//                return true;
//            if((entity1.contains("swee") && entity2.contains("youyou")) || (entity2.contains("swee") && entity1.contains("youyou")))
//                return true;
//            if((entity1.contains("cow") && entity2.contains("cow")))
//                return true;
            }

        }
        return false;
//        Optional<IncubatorRecipe> match = level.getRecipeManager()
//                .getRecipeFor(IncubatorRecipe.Type.INSTANCE, inventory, level);
//
//        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
//                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())
//                && hasFuelSlot(entity);
    }

//    private static boolean has0Slot(IncubatorBlockEntity entity) {
//        return entity.itemHandler.getStackInSlot(0).getItem() == Items.ROTTEN_FLESH;
//    }

//    private static boolean has1Slot(IncubatorBlockEntity entity) {
//        return entity.itemHandler.getStackInSlot(1).getItem() == ItemInit.LYEYE.get();
//    }

    private static boolean hasFuelSlot(IncubatorBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(2).getItem() == Items.CAKE;
    }

    private static void craftItem(IncubatorBlockEntity entity,BlockPos pos ) {
        World level = entity.level;
        Inventory inventory = new Inventory(entity.itemHandler.getSlots());

        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
            System.out.println(inventory.getItem(0).getItem());
        }

        if(inventory.getItem(0).getItem() instanceof ItemPalball &&
                inventory.getItem(1).getItem() instanceof ItemPalball)
        {
            CompoundNBT entity1Tag = NBTHelper.getTag(inventory.getItem(0), "StoredEntity");
            CompoundNBT entity2Tag = NBTHelper.getTag(inventory.getItem(1), "StoredEntity");

            String entity1 = entity1Tag.getString("id");
            String entity2 = entity2Tag.getString("id");
            String combinedS= PalUtils.compareString(entity1,entity2);
            String containS= PalUtils.containString(entity1,entity2);

            if(PAL_ENTITY_MAP.containsKey(combinedS))
            {
                //System.out.println("combinedS");
                spawnNewEntity(level,PAL_ENTITY_MAP.get(combinedS).get(),pos);
            }
            else if(PAL_ENTITY_MAP2.containsKey(containS))
            {
                spawnNewEntity(level,PAL_ENTITY_MAP2.get(containS).get(),pos);
            }
//            if(entity1.contains("sheep") && entity2.contains("sheep"))
//            {
//                YouYou ent = new YouYou(EntityInit.YOUYOU.get(),level);
//                spawnNewEntity(level,ent,pos);
//            }
//            if(entity1.contains("cat") && entity2.contains("cat"))
//            {
//                Pal ent = new Pal(EntityInit.CATTIVA.get(),level);
//                spawnNewEntity(level,ent,pos);
//            }
//            if(entity1.contains("chicken") && entity2.contains("chicken"))
//            {
//                Chikipi ent = new Chikipi(EntityInit.CHIKIPI.get(),level);
//                spawnNewEntity(level,ent,pos);
//            }
//            if((entity1.contains("tanzee") && entity2.contains("youyou")) || (entity2.contains("tanzee") && entity1.contains("youyou")))
//            {
//                Jolthog ent = new Jolthog(EntityInit.JOLTHOG.get(),level);
//                spawnNewEntity(level,ent,pos);
//            }
//            if((entity1.contains("swee") && entity2.contains("youyou")) || (entity2.contains("swee") && entity1.contains("youyou")))
//            {
//                Depresso ent = new Depresso(EntityInit.DEPRESSO.get(),level);
//                spawnNewEntity(level,ent,pos);
//            }
//
//            if((entity1.contains("cow") && entity2.contains("cow")))
//            {
//                Mozzarina ent = new Mozzarina(EntityInit.MOZZARINA.get(),level);
//                spawnNewEntity(level,ent,pos);
//            }

            entity.resetProgress();
            entity.itemHandler.extractItem(2,1, false);
        }

//        Optional<IncubatorRecipe> match = level.getRecipeManager()
//                .getRecipeFor(IncubatorRecipe.Type.INSTANCE, inventory, level);
//
//        if(match.isPresent()) {
//            //entity.itemHandler.extractItem(0,1, false);
//            //entity.itemHandler.extractItem(1,1, false);
//            entity.itemHandler.extractItem(2,1, false);
//
//            entity.itemHandler.setStackInSlot(3, new ItemStack(match.get().getResultItem().getItem(),
//                    entity.itemHandler.getStackInSlot(3).getCount() + 1));
//
//            entity.resetProgress();
//        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void spawnNewEntity(World level,LivingEntity ent, BlockPos pos)
    {

        ent.moveTo(pos.getX(), pos.getY()+1, pos.getZ());
        if(ent instanceof TameableEntity)
            ((TameableEntity) ent).setTame(true);
        if(ent instanceof Pal) {
            ((Pal) ent).setIsTamed(1);

        }
        //System.out.println("spawn");
        level.addFreshEntity(ent);
    }

    private static boolean canInsertItemIntoOutputSlot(Inventory inventory, ItemStack output) {
        return inventory.getItem(3).getItem() == output.getItem() || inventory.getItem(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(Inventory inventory) {
        return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
    }

    private static boolean notReachLimit(IncubatorBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(3).getCount()<64;
    }

//    @Override
//    public BlockState getBlockState() {
//        return this.blockState;
//     }
    private static Map<String, Supplier<LivingEntity>> PAL_ENTITY_MAP = new HashMap<>();
    private static Map<String, Supplier<LivingEntity>> PAL_ENTITY_MAP2 = new HashMap<>();

    static{
//        PAL_ENTITY_MAP.put("sheep-sheep", 0);
//        PAL_ENTITY_MAP.put("chicken-chicken",1);
//        PAL_ENTITY_MAP.put("cat-cat",2);
//        PAL_ENTITY_MAP.put("cow-cow",3);
//        PAL_ENTITY_MAP.put("tanzee-youyou",4);
//        PAL_ENTITY_MAP.put("swee-youyou", 5);
//        PAL_ENTITY_MAP.put("skeleton-squid",6);
    }






}

