package lv.cebbys.mcmods.respro.api.initializer.blockstate.multipart;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface MultipartWhenResourceInitializer {
    @NotNull MultipartWhenResourceInitializer setWhen(
            @NotNull Consumer<MultipartBlockPropertyResourceInitializer> consumer
    );
}
