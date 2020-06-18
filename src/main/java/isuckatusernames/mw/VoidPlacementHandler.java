package isuckatusernames.mw;

import net.fabricmc.fabric.api.dimension.v1.EntityPlacer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@SuppressWarnings("deprecation")
public class VoidPlacementHandler {
	public static EntityPlacer enter(final BlockPos portalPos) {
		return (entity, destination, direction, v, v1) -> {
			BlockPos pos = enterVoid(entity, destination, portalPos);
			return new BlockPattern.TeleportTarget(Vec3d.of(pos).add(0.5, 0, 0.5), Vec3d.ZERO, 0);
		};
	}

	public static EntityPlacer leave(final BlockPos portalPos) {
		return (entity, destination, direction, v, v1) -> {
			BlockPos pos = leaveVoid(entity, destination, portalPos);
			return new BlockPattern.TeleportTarget(Vec3d.of(pos).add(0.5, 0, 0.5), Vec3d.ZERO, 0);
		};
	}

	private static BlockPos enterVoid(Entity entity, ServerWorld newWorld, BlockPos portalPos) {
		BlockPos spawnPos = new BlockPos(portalPos.getX(), 100, portalPos.getZ());
		spawnVoidPlatform(newWorld, spawnPos.down());
		return spawnPos;
	}

	private static BlockPos leaveVoid(Entity entity, ServerWorld newWorld, BlockPos portalPos) {
		return newWorld.getTopPosition(Heightmap.Type.MOTION_BLOCKING, portalPos).up();
	}

	private static void spawnVoidPlatform(World world, BlockPos pos) {
		if (world.getBlockState(pos).getBlock() != MumblingWoods.PORTAL_BLOCK) {
			BlockState platformBlock = randomTerracotta();
			for (int x = -3; x < 4; x++) {
				for (int z = -3; z < 4; z++) {
					if (world.isAir(pos.add(x, 0, z))) {
						world.setBlockState(pos.add(x, 0, z), platformBlock);
					}

				}
			}
			world.setBlockState(pos, MumblingWoods.PORTAL_BLOCK.getDefaultState());
			for (Direction facing : Direction.values()) {
				if (facing.getAxis().isHorizontal()) {
					world.setBlockState(pos.up().offset(facing), Blocks.TORCH.getDefaultState());
				}
			}

		}
	}

	public static BlockState randomTerracotta() {
		Random random = new Random();

		List<BlockState> terracottaBlocks = new ArrayList<>();
		Registry.BLOCK.forEach(block -> {
			if (Registry.BLOCK.getId(block).getPath().endsWith("_terracotta") && !Registry.BLOCK.getId(block).getPath().endsWith("_glazed_terracotta")) {
				terracottaBlocks.add(block.getDefaultState());
			}
		});

		return terracottaBlocks.get(random.nextInt(terracottaBlocks.size()));
	}

}
