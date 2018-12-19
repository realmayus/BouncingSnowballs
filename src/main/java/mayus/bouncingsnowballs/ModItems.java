package mayus.bouncingsnowballs;

import mayus.bouncingsnowballs.launcher.ItemLauncher;
import mayus.bouncingsnowballs.snowball.ItemCSnowball;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    @GameRegistry.ObjectHolder("bouncingsnowballs:launcher")
    public static ItemLauncher itemLauncher;

    @GameRegistry.ObjectHolder("bouncingsnowballs:csnowball")
    public static ItemCSnowball itemCSnowball;



    @SideOnly(Side.CLIENT)
    public static void initModels() {
        ModelLoader.setCustomModelResourceLocation(itemLauncher, 0, new ModelResourceLocation("bouncingsnowballs:launcher"));
        //ModelLoader.setCustomModelResourceLocation(itemCSnowball, 0, new ModelResourceLocation("bouncingsnowballs:csnowball"));


    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemCSnowball, 0, new ModelResourceLocation(itemCSnowball.getRegistryName(), "inventory"));

    }

    public static void register(IForgeRegistry<Item> registry) {
        registry.register(new ItemLauncher());
        registry.register(new ItemCSnowball());

    }

}
