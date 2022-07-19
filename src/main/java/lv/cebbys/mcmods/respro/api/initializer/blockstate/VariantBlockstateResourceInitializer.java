package lv.cebbys.mcmods.respro.api.initializer.blockstate;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface VariantBlockstateResourceInitializer {
    @NotNull VariantBlockstateResourceInitializer setVariant(
            @NotNull Consumer<BlockVariantPropertyResourceInitializer> propertyConsumer,
            @NotNull Consumer<BlockVariantResourceInitializer> variantConsumer
    );

    default @NotNull VariantBlockstateResourceInitializer setVariant(@NotNull Consumer<BlockVariantResourceInitializer> consumer) {
        return setVariant(BlockVariantPropertyResourceInitializer::setNormal, consumer);
    }
}
