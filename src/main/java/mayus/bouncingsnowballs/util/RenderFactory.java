package mayus.bouncingsnowballs.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFactory implements IRenderFactory {

    public final Item item;

    public RenderFactory(Item item) {

        this.item = item;

    }

    @Override
    public Render createRenderFor(RenderManager manager) {

        return new RenderSnowball(manager, item, Minecraft.getMinecraft().getRenderItem());

    }

}
