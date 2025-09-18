package org.lilbrocodes.redacted.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.WorldRenderer;
import org.lilbrocodes.redacted.config.Configs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Inject(method = "renderWorldBorder", at = @At("HEAD"), cancellable = true)
    public void redacted$cancelWorldBorder(Camera camera, CallbackInfo ci) {
        if (Configs.SERVER.enabled) ci.cancel();
    }
}
