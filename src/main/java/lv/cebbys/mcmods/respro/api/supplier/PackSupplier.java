package lv.cebbys.mcmods.respro.api.supplier;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.Pack.PackConstructor;
import net.minecraft.server.packs.repository.RepositorySource;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface PackSupplier<T extends Pack> extends RepositorySource {
    @NotNull
    ResourceLocation getSupplierId();

    @NotNull
    T getPack();

    @Override
    default void loadPacks(Consumer<Pack> profileAdder, PackConstructor constructor) {
        profileAdder.accept(getPack());
    }
}
