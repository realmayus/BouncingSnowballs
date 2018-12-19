package mayus.bouncingsnowballs;


import mayus.bouncingsnowballs.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.MODVERSION, dependencies = "required-after:forge@[14.23.5.2768,)", useMetadata = true)
public class Main {

    public static final String MODID = "bouncingsnowballs";
    public static final String MODNAME = "Bouncing Snowballs";
    public static final String MODVERSION= "0.0.1";

    @SidedProxy(clientSide = "mayus.bouncingsnowballs.proxy.ClientProxy", serverSide = "mayus.bouncingsnowballs.proxy.ServerProxy")
    public static CommonProxy proxy;

    public static CreativeTabs creativeTab = new CreativeTabs("bouncingsnowballs") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.itemLauncher);
        }
    };



    @Mod.Instance
    public static Main instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
