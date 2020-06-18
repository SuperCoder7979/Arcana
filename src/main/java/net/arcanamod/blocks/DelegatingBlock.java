package net.arcanamod.blocks;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class DelegatingBlock extends Block{
	private final Block parentBlock;
	
	public DelegatingBlock(Block blockIn){
		super(Properties.from(blockIn));
		this.parentBlock = blockIn;
		
		// Refill the state container - Block does this too early
		StateContainer.Builder<Block, BlockState> builder = new StateContainer.Builder<>(this);
		fillStateContainer(builder);
		stateContainer = builder.create((__, properties) -> new BlockState(this, properties));
		setDefaultState(stateContainer.getBaseState());
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder){
		// parentBlock.fillStateContainer(builder);
		// protected access
		// can't AT it to public because its overriden with "less" (i.e. protected) visibility.
		if(parentBlock != null)
			try{
				Method fillStateContainer = Block.class.getDeclaredMethod("fillStateContainer", StateContainer.Builder.class);
				fillStateContainer.setAccessible(true);
				fillStateContainer.invoke(parentBlock, builder);
				fillStateContainer.setAccessible(false);
			}catch(IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
				e.printStackTrace();
				System.err.println("Unable to delegate blockstate!");
			}
	}
	
	public IFluidState getFluidState(BlockState state){
		return parentBlock.getFluidState(state);
	}
	
	protected BlockState switchBlock(BlockState state, Block block){
		return new BlockState(block, state.getValues());
	}
	
	public BlockState getStateForPlacement(BlockItemUseContext context){
		BlockState placement = parentBlock.getStateForPlacement(context);
		return placement != null
				? switchBlock(placement, this)
				: null;
	}
	
	public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction){
		return switchBlock(parentBlock.rotate(state, world, pos, direction), this);
	}
	
	public BlockState rotate(BlockState state, Rotation rot){
		return switchBlock(parentBlock.rotate(state, rot), this);
	}
	
	public BlockState mirror(BlockState state, Mirror mirror){
		return switchBlock(parentBlock.mirror(state, mirror), this);
	}
	
	/*public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos){
		return switchBlock(parentBlock.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos), this);
	}*/
	
	/*public BlockState getExtendedState(BlockState state, IBlockReader world, BlockPos pos){
		return switchBlock(parentBlock.getExtendedState(state, world, pos), this);
	}*/
	
	public BlockState getStateForPlacement(BlockState state, Direction facing, BlockState state2, IWorld world, BlockPos pos1, BlockPos pos2, Hand hand){
		return switchBlock(parentBlock.getStateForPlacement(state, facing, state2, world, pos1, pos2, hand), this);
	}
	
	public BlockState getStateAtViewpoint(BlockState state, IBlockReader world, BlockPos pos, Vec3d viewpoint){
		return switchBlock(parentBlock.getStateAtViewpoint(state, world, pos, viewpoint), this);
	}
	
	public boolean isTransparent(BlockState state){
		return parentBlock != null && parentBlock.isTransparent(state);
	}
	
	public boolean isFoliage(BlockState state, IWorldReader world, BlockPos pos){
		return parentBlock.isFoliage(state, world, pos);
	}
	
	public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face){
		return parentBlock.getFireSpreadSpeed(state, world, pos, face);
	}
	
	public float getExplosionResistance(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity exploder, Explosion explosion){
		return parentBlock.getExplosionResistance(state, world, pos, exploder, explosion);
	}
	
	public boolean addRunningEffects(BlockState state, World world, BlockPos pos, Entity entity){
		return parentBlock.addRunningEffects(state, world, pos, entity);
	}
	
	public boolean addDestroyEffects(BlockState state, World world, BlockPos pos, ParticleManager manager){
		return parentBlock.addDestroyEffects(state, world, pos, manager);
	}
	
	public boolean addHitEffects(BlockState state, World worldObj, RayTraceResult target, ParticleManager manager){
		return parentBlock.addHitEffects(state, worldObj, target, manager);
	}
	
	public boolean addLandingEffects(BlockState state1, ServerWorld worldserver, BlockPos pos, BlockState state2, LivingEntity entity, int numberOfParticles){
		return parentBlock.addLandingEffects(state1, worldserver, pos, state2, entity, numberOfParticles);
	}
	
	/*public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random){
		parentBlock.randomTick(state, worldIn, pos, random);
	}
	
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand){
		parentBlock.tick(state, worldIn, pos, rand);
	}*/
	
	public int getLightValue(BlockState state){
		return parentBlock != null ? parentBlock.getLightValue(state) : super.getLightValue(state);
	}
	
	public float getSlipperiness(){
		return parentBlock.getSlipperiness();
	}
	
	public void beginLeaveDecay(BlockState state, IWorldReader world, BlockPos pos){
		parentBlock.beginLeaveDecay(state, world, pos);
	}
	
	public boolean isToolEffective(BlockState state, ToolType tool){
		return parentBlock.isToolEffective(state, tool);
	}
	
	@Nullable
	public Direction[] getValidRotations(BlockState state, IBlockReader world, BlockPos pos){
		return parentBlock.getValidRotations(state, world, pos);
	}
	
	public boolean canHarvestBlock(BlockState state, IBlockReader world, BlockPos pos, PlayerEntity player){
		return parentBlock.canHarvestBlock(state, world, pos, player);
	}
	
	@Override
	public boolean ticksRandomly(BlockState state){
		return parentBlock.ticksRandomly(state);
	}
	
	@Override
	public void animateTick(BlockState state, World world, BlockPos position, Random rand){
		parentBlock.animateTick(state, world, position, rand);
	}
	
	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader world, BlockPos pos){
		return parentBlock.propagatesSkylightDown(state, world, pos);
	}
	
	@Override
	public int tickRate(IWorldReader world){
		return parentBlock.tickRate(world);
	}
	
	@Override
	public void dropXpOnBlockBreak(World world, BlockPos pos, int num){
		parentBlock.dropXpOnBlockBreak(world, pos, num);
	}
	
	@Override
	public void onExplosionDestroy(World world, BlockPos pos, Explosion explosion){
		parentBlock.onExplosionDestroy(world, pos, explosion);
	}
	
	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity){
		parentBlock.onEntityWalk(world, pos, entity);
	}
	
	@Override
	public boolean canSpawnInBlock(){
		return parentBlock.canSpawnInBlock();
	}
	
	@Override
	public void onFallenUpon(World world, BlockPos pos, Entity entity, float num){
		parentBlock.onFallenUpon(world, pos, entity, num);
	}
	
	@Override
	public void onLanded(IBlockReader world, Entity entity){
		parentBlock.onLanded(world, entity);
	}
	
	@Override
	public float getSpeedFactor(){
		return parentBlock.getSpeedFactor();
	}
	
	@Override
	public float getJumpFactor(){
		return parentBlock.getJumpFactor();
	}
	
	@Override
	public void onProjectileCollision(World world, BlockState state, BlockRayTraceResult trace, Entity entity){
		parentBlock.onProjectileCollision(world, state, trace, entity);
	}
	
	@Override
	public void fillWithRain(World world, BlockPos pos){
		parentBlock.fillWithRain(world, pos);
	}
	
	@Override
	public OffsetType getOffsetType(){
		return parentBlock.getOffsetType();
	}
	
	@Override
	public boolean isVariableOpacity(){
		return parentBlock.isVariableOpacity();
	}
	
	@Override
	public float getSlipperiness(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity){
		return parentBlock.getSlipperiness(state, world, pos, entity);
	}
	
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state){
		return parentBlock.getHarvestTool(state);
	}
	
	@Override
	public int getHarvestLevel(BlockState state){
		return parentBlock.getHarvestLevel(state);
	}
	
	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable){
		return parentBlock.canSustainPlant(state, world, pos, facing, plantable);
	}
}