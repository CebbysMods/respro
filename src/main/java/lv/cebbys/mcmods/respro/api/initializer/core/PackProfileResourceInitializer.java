package lv.cebbys.mcmods.respro.api.initializer.core;

import net.minecraft.server.packs.repository.Pack.Position;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface PackProfileResourceInitializer {
    @NotNull PackProfileResourceInitializer setPackIcon(@NotNull Consumer<ImageResourceInitializer> consumer);

    @NotNull PackProfileResourceInitializer setPackName(@NotNull Consumer<StringResourceInitializer> consumer);

    @NotNull PackProfileResourceInitializer setPackSource(@NotNull Consumer<StringResourceInitializer> consumer);

    @NotNull PackProfileResourceInitializer setPackMeta(@NotNull Consumer<MetaResourceInitializer> consumer);

    @NotNull PackProfileResourceInitializer setPackInsertionPosition(@NotNull Position insertionPosition);

    @NotNull PackProfileResourceInitializer setAlwaysEnabled(boolean packAlwaysEnabled);

    @NotNull PackProfileResourceInitializer setPinned(boolean packPinned);
}
