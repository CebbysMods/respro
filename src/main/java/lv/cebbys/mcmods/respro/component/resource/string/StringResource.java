package lv.cebbys.mcmods.respro.component.resource.string;

import lombok.Getter;
import lv.cebbys.mcmods.respro.api.initializer.core.StringResourceInitializer;
import lv.cebbys.mcmods.respro.component.resource.core.AbstractStringResource;
import lv.cebbys.mcmods.respro.exception.ResourceValidationException;
import net.minecraft.ResourceLocationException;
import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;

@Getter
public final class StringResource extends AbstractStringResource implements StringResourceInitializer {
    private String content;

    public StringResource(@NotNull String textContent) {
        content = textContent;
    }

    public StringResource() {
        content = null;
    }

    @Override
    public @NotNull StringResourceInitializer setTranslatableText(@NotNull String translationKey) {
        content = translationKey;
        return this;
    }

    @Override
    public @NotNull StringResourceInitializer setText(@NotNull String text) {
        content = text;
        return this;
    }

    @Override
    public @NotNull("StringResource content is null") String getAsString() {
        return content;
    }

    @SuppressWarnings("all")
    @Override
    public void validate() throws ResourceValidationException {
        try {
            getAsString();
        } catch (Exception e) {
            throw new ResourceLocationException("TextResource content failed validation", e);
        }
    }

    @Override
    public boolean belongsTo(@NotNull PackType type) {
        return true;
    }
}
