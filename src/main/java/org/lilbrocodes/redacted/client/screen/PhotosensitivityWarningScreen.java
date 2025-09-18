package org.lilbrocodes.redacted.client.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.MultilineTextWidget;
import net.minecraft.text.Text;
import org.lilbrocodes.redacted.config.Configs;

public class PhotosensitivityWarningScreen extends Screen {
    public PhotosensitivityWarningScreen() {
        super(Text.empty());
    }

    @Override
    protected void init() {
        int centerX = width / 2;
        int centerY = height / 2 + 10;

        addDrawable(new MultilineTextWidget(centerX - 250, centerY - 40, Text.translatable("redacted.warning"), textRenderer).setCentered(true).setMaxRows(5).setMaxWidth(500));
        addDrawableChild(new ButtonWidget.Builder(Text.translatable("redacted.warning.confirm"), button -> {
            Configs.CLIENT.photosensitivityMode = true;
            close();
        }).dimensions(centerX - 75, centerY + 20, 150, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public void close() {
        Configs.CLIENT.seenPhotosensitivityPopup = true;
        Configs.CLIENT.save();
        super.close();
    }
}
