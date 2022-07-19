package lv.cebbys.mcmods.respro.api.initializer.core;

import org.jetbrains.annotations.NotNull;

public interface StringResourceInitializer {
    @NotNull StringResourceInitializer setTranslatableText(@NotNull String translationKey);

    @NotNull StringResourceInitializer setText(@NotNull String text);
}
