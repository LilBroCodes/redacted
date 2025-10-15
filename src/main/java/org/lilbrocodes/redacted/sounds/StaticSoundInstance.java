package org.lilbrocodes.redacted.sounds;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.AbstractSoundInstance;
import net.minecraft.client.sound.TickableSoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.random.Random;
import org.lilbrocodes.redacted.config.Configs;


public class StaticSoundInstance extends AbstractSoundInstance implements TickableSoundInstance {
    private final MinecraftClient client;
    private boolean done = false;

    private float smoothedVolume = 0f;
    private static final float SMOOTHING = 0.12f;
    private static final float STOP_THRESHOLD = 0.005f;

    public StaticSoundInstance(MinecraftClient client, SoundEvent sound) {
        super(sound.getId(), SoundCategory.MASTER, Random.create());
        this.client = client;

        this.repeat = true;
        this.repeatDelay = 0;
        this.volume = 0f;
        this.smoothedVolume = 0f;
        this.relative = true;
    }

    @Override
    public void tick() {
        if (client.world == null || client.player == null || client.isPaused()) {
            this.done = true;
            return;
        }

        var border = client.world.getWorldBorder();
        double d = border.getDistanceInsideBorder(client.player);
        double C = Configs.SERVER.renderDistance;
        double K = Configs.SERVER.killDistance;

        float target = (float) ((d - C) / (K - C));
        target = Math.max(0f, Math.min(1f, target)) * 2.5f;

        smoothedVolume += (target - smoothedVolume) * SMOOTHING;

        if (smoothedVolume < 0f) smoothedVolume = 0f;

        this.volume = smoothedVolume;

        if (target <= 0f && smoothedVolume <= STOP_THRESHOLD) {
            this.done = true;
        }
    }

    @Override
    public boolean isDone() {
        return done;
    }
}
