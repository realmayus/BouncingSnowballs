package mayus.bouncingsnowballs.snowball;

import mayus.bouncingsnowballs.Main;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemCSnowball extends Item
{

    public static final ResourceLocation SNOWBALL = new ResourceLocation(Main.MODID, "csnowball");

    public ItemCSnowball()
    {
        maxStackSize = 16;
        setCreativeTab(Main.creativeTab);
        setTranslationKey("csnowball");
        setRegistryName(SNOWBALL);
    }

    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {

        if(worldIn.isRemote) {
            playerIn.sendMessage(new TextComponentString("You have to use the Snowball Launcher!"));
        }

        return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
