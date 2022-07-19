package lv.cebbys.mcmods.respro.component.supplier;

import lombok.AllArgsConstructor;
import lv.cebbys.mcmods.respro.api.supplier.PackSupplier;
import lv.cebbys.mcmods.respro.component.resource.pack.ResproPackResources;
import lv.cebbys.mcmods.respro.utility.ResourceLocationUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.repository.Pack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Supplier;

@AllArgsConstructor
public class SimplePackSupplier<T extends Pack> {
    private final Supplier<ResproPackResources<?, ?>> packResources;
    private final Function<ResproPackResources<?, ?>, T> packCreator;

    public @NotNull ResourceLocation getSupplierId() {
        return ResourceLocationUtils.suffix(packResources.get().getId(), "_supplier");
    }

    public @NotNull T getPack() {
        return packCreator.apply(packResources.get());
    }
}
