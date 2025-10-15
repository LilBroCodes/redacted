package org.lilbrocodes.redacted.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import org.lilbrocodes.redacted.config.Configs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Shadow @Final private MinecraftClient client;

    @ModifyExpressionValue(method = "renderVignetteOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/border/WorldBorder;getDistanceInsideBorder(Lnet/minecraft/entity/Entity;)D"))
    public double redacted$removeBorderVignette(double original) {
        if (Configs.SERVER.enabled) {
            assert client.world != null;
            return client.world.getWorldBorder().getWarningBlocks() + 1;
        }
        return original;
    }
}
