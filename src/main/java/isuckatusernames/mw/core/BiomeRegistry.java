package isuckatusernames.mw.core;

import isuckatusernames.mw.MumblingWoods;
import isuckatusernames.mw.biome.MumblingWoodlands;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class BiomeRegistry {
    public static final Biome MUMBLING_WOODLANDS = new MumblingWoodlands();

    public static void registerBiomes() {
        registerBiome(MUMBLING_WOODLANDS, "mumbling_woodlands");
    }

    private static void registerBiome(Biome biome, String id) {
        Registry.register(Registry.BIOME, new Identifier(MumblingWoods.MODID, id), biome);
    }
}
