package mayus.bouncingsnowballs.launcher;

import mayus.bouncingsnowballs.Main;
import mayus.bouncingsnowballs.ModItems;
import mayus.bouncingsnowballs.snowball.EntityCSnowball;
import mayus.bouncingsnowballs.snowball.ItemCSnowball;
import mayus.bouncingsnowballs.util.API;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLauncher extends Item {

    public static final ResourceLocation LAUNCHER = new ResourceLocation(Main.MODID, "launcher");

    public ItemLauncher() {
        setCreativeTab(Main.creativeTab);
        setTranslationKey("launcher");
        setRegistryName(LAUNCHER);
    }


    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {

        //Check if player has enough ammunition
        if(API.getCountOfItem(playerIn, ModItems.itemCSnowball) > 0) {
            if (!playerIn.capabilities.isCreativeMode)
            {
                API.shrinkInvItem(playerIn, ModItems.itemCSnowball);
            }

            worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!worldIn.isRemote)
            {
                EntityCSnowball entityCSnowball = new EntityCSnowball(worldIn, playerIn);
                entityCSnowball.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
                worldIn.spawnEntity(entityCSnowball);
            }


        } else {
            if(worldIn.isRemote) {
                playerIn.sendMessage(new TextComponentString("You don't have enough ammunition"));
            }
        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItemMainhand());
    }

}
