package isuckatusernames.mw.mixin;

import isuckatusernames.mw.MumblingWoods;
import net.minecraft.util.registry.RegistryTracker;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DimensionType.class)
public class MixinDimensionType {

    @Inject(at = @At("HEAD"), method = "addRegistryDefaults", cancellable = true)
    private static void addMumblingWoodsRegistryDefault(RegistryTracker.Modifiable registryTracker, CallbackInfoReturnable<RegistryTracker.Modifiable> cir) {
        registryTracker.addDimensionType(MumblingWoods.MUMBLING_WOODS_REGISTRY_KEY, MumblingWoods.MUMBLING_WOODS);
    }
}
