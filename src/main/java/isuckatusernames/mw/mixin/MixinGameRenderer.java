package isuckatusernames.mw.mixin;

import isuckatusernames.mw.MumblingWoods;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(at = @At("HEAD"), method = "getViewDistance", cancellable = true)
    private void yeet(CallbackInfoReturnable<Float> cir) {

        final World world = this.client.world;

        if (world.getWorld().getRegistryKey() == MumblingWoods.MUMBLING_WOODS_WORLD)
            cir.setReturnValue(0.1F);
    }
}
