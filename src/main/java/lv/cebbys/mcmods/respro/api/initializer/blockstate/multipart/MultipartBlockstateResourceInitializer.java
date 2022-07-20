package lv.cebbys.mcmods.respro.api.initializer.blockstate.multipart;

import lv.cebbys.mcmods.respro.api.initializer.blockstate.BlockVariantResourceInitializer;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface MultipartBlockstateResourceInitializer {
    @NotNull MultipartBlockstateResourceInitializer setWhen(
            @NotNull Consumer<MultipartWhenResourceInitializer> whenConsumer,
            @NotNull Consumer<BlockVariantResourceInitializer> applyConsumer
    );

    @NotNull MultipartBlockstateResourceInitializer setApply(
            @NotNull Consumer<BlockVariantResourceInitializer> consumer
    );
}
