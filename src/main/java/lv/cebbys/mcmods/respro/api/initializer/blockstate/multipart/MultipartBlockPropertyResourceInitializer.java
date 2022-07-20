package lv.cebbys.mcmods.respro.api.initializer.blockstate.multipart;

import org.jetbrains.annotations.NotNull;

public interface MultipartBlockPropertyResourceInitializer {
    @NotNull MultipartBlockPropertyResourceInitializer setProperty(
            @NotNull String key,
            @NotNull String... values
    );
}
