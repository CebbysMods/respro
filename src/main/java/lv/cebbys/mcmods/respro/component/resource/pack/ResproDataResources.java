package lv.cebbys.mcmods.respro.component.resource.pack;

import lv.cebbys.mcmods.respro.api.initializer.kleeslabs.KleeSlabsCompatResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.pack.DataResourcesInitializer;
import lv.cebbys.mcmods.respro.api.initializer.custre.CustreRecipeResourceInitializer;
import lv.cebbys.mcmods.respro.api.pack.Data;
import lv.cebbys.mcmods.respro.api.supplier.DataSupplier;
import lv.cebbys.mcmods.respro.component.supplier.SimpleDataSupplier;
import lv.cebbys.mcmods.respro.utility.ResourceLocationUtils;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ResproDataResources extends ResproPackResources<ResproDataResources, DataSupplier> implements DataResourcesInitializer {
    @Override
    public DataSupplier getSupplier() {
        return new SimpleDataSupplier(this::getInstance, (resources) -> new Data(
                resources.getId(), resources.getProfile(), () -> resources
        ));
    }

    @Override
    protected ResproDataResources getInstance() {
        return this;
    }

    @Override
    public @NotNull DataResourcesInitializer setCustreRecipe(@NotNull ResourceLocation id, @NotNull Consumer<CustreRecipeResourceInitializer> consumer) {
        return setResource(
                CustreRecipeResourceInitializer.class,
                ResourceLocationUtils.wrapped("custre/", id, ".json"),
                consumer
        );
    }

    @Override
    public @NotNull("DataResourcesInitializer is null") DataResourcesInitializer setKleeSlabsCompatibility(
            @NotNull("ResourceLocation provided was null") ResourceLocation id,
            @NotNull("CustreRecipeResourceInitializer consumer was null") Consumer<KleeSlabsCompatResourceInitializer> consumer
    ) {
        return setResource(
                KleeSlabsCompatResourceInitializer.class,
                ResourceLocationUtils.wrapped("kleeslabs_compat/", id, ".json"),
                consumer
        );
    }
}
