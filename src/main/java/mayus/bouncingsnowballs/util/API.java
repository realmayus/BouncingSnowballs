package mayus.bouncingsnowballs.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class API {

    public static int getCountOfItem(EntityPlayer player, Item item) {
        int count = 0;

        //Check for main Inventory
        for(ItemStack is : player.inventory.mainInventory) {
            if(is.getItem() == item) {
                count = count + is.getCount();
            }
        }

        //Check for offhand
        for(ItemStack is : player.inventory.offHandInventory) {
            if(is.getItem() == item) {
                count = count + is.getCount();
            }
        }
        return count;
    }

    public static void setCountOfInvItem(EntityPlayer player, Item item, int count) {
        for(ItemStack is : player.inventory.mainInventory) {
            if(is.getItem() == item) {
                is.setCount(count);
                return;
            }
        }

        //Check for offhand
        for(ItemStack is : player.inventory.offHandInventory) {
            if(is.getItem() == item) {
                is.setCount(count);
                return;
            }
        }
    }

    public static void shrinkInvItem(EntityPlayer player, Item item) {
        for(ItemStack is : player.inventory.mainInventory) {
            if(is.getItem() == item) {
                is.setCount(is.getCount() - 1);
                return;
            }
        }

        //Check for offhand
        for(ItemStack is : player.inventory.offHandInventory) {
            if(is.getItem() == item) {
                is.setCount(is.getCount() - 1);
                return;
            }
        }
    }

}
