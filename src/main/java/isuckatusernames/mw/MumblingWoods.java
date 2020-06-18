package isuckatusernames.mw;

import isuckatusernames.mw.core.BiomeRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.OptionalLong;

public class MumblingWoods implements ModInitializer {
	public static String MODID = "mumbling_woods";



	public static BlockPortal PORTAL_BLOCK;
	public static ItemGroup SVW_GROUP = FabricItemGroupBuilder.build(new Identifier("mumbling_woods", "mumbling_woods"), () -> new ItemStack(PORTAL_BLOCK));
	public static final RegistryKey<World> MUMBLING_WOODS_WORLD = RegistryKey.of(Registry.DIMENSION, new Identifier("mumbling_woods", "mumbling_woods"));

	public static final DimensionType MUMBLING_WOODS = new MumblingWoodsDimensionType(OptionalLong.of(6000L), false, false, false, false, false, true, false, false, false, 256, BlockTags.INFINIBURN_END.getId(), 0.0F);
	public static final RegistryKey<DimensionType> MUMBLING_WOODS_REGISTRY_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY, new Identifier("mumbling_woods_type"));;


	@Override
	public void onInitialize() {
		PORTAL_BLOCK = new BlockPortal();
		Registry.register(Registry.BLOCK, new Identifier("mumbling_woods", "void_portal"), PORTAL_BLOCK);
		BiomeRegistry.registerBiomes();
		BlockItem blockItem = new BlockItem(PORTAL_BLOCK, new Item.Settings().group(SVW_GROUP));
		blockItem.appendBlocks(Item.BLOCK_ITEMS, blockItem);

		Registry.register(Registry.ITEM, new Identifier("mumbling_woods", "void_portal"), blockItem);
	}
}