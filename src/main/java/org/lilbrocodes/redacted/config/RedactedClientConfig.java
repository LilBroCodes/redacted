package org.lilbrocodes.redacted.config;

import me.fzzyhmstrs.fzzy_config.api.FileType;
import me.fzzyhmstrs.fzzy_config.api.SaveType;
import me.fzzyhmstrs.fzzy_config.config.Config;
import org.jetbrains.annotations.NotNull;
import org.lilbrocodes.redacted.Redacted;

public class RedactedClientConfig extends Config {
    public RedactedClientConfig() {
        super(Redacted.identify("client_config"));
    }

    @Override
    public int defaultPermLevel() {
        return 0;
    }

    @Override
    public @NotNull FileType fileType() {
        return FileType.JSONC;
    }

    @Override
    public @NotNull SaveType saveType() {
        return SaveType.SEPARATE;
    }

    @Prefix("Turns the static shader for the world border into a vignette for people with photosensitivity")
    public boolean photosensitivityMode = true;

    @Prefix("Used to track whether you have seen the photosensitivity popup. You can disable this, which will make the popup appear again on the next world join")
    public boolean seenPhotosensitivityPopup = false;

    @Override
    public @NotNull String translationKey() {
        return "redacted.client_config";
    }

    @Override
    public boolean hasTranslation() {
        return true;
    }
}
