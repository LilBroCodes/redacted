package org.lilbrocodes.redacted.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import org.lilbrocodes.redacted.registry.ModShaders;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow @Final MinecraftClient client;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;pop()V", shift = At.Shift.BEFORE))
    public void redacted$renderStatic(float tickDelta, long startTime, boolean tick, CallbackInfo ci) {
        ModShaders.renderStatic(client, tickDelta);
    }
}
