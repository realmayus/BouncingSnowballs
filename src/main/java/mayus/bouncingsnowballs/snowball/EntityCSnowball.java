package mayus.bouncingsnowballs.snowball;

import mayus.bouncingsnowballs.Main;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCSnowball extends EntityThrowable {


    public static final ResourceLocation SNOWBALL = new ResourceLocation(Main.MODID, "csnowball");

    public EntityCSnowball(World worldIn)
    {
        super(worldIn);
    }

    public EntityCSnowball(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityCSnowball(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public static void registerFixesCSnowball(DataFixer fixer)
    {
        EntityThrowable.registerFixesThrowable(fixer, "CSnowball");
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            int i = 2;

            if (result.entityHit instanceof EntityBlaze)
            {
                i = 3;
            }

            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);
            this.setDead();
            return;
        }

        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
            System.out.println(thrower.getName());
            System.out.println(result.getBlockPos().toString());
            Explosion explosion = new Explosion(this.world, thrower, result.getBlockPos().getX(), result.getBlockPos().getY(), result.getBlockPos().getZ(), (float)5, true, true);

            explosion.doExplosionA();
            explosion.doExplosionB(true);

            for(BlockPos blockPos : explosion.getAffectedBlockPositions()) {
                world.extinguishFire(null, blockPos, EnumFacing.UP);

                if(world.getBlockState(blockPos.add(0, -1, 0)) != Blocks.AIR.getDefaultState()) {
                    world.setBlockState(blockPos, Blocks.SNOW_LAYER.getDefaultState());
                }

            }

            this.setDead();
        }
    }
}
