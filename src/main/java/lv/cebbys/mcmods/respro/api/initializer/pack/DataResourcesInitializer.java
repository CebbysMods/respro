package lv.cebbys.mcmods.respro.api.initializer.pack;

import lv.cebbys.mcmods.respro.api.initializer.custre.CustreRecipeResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.kleeslabs.KleeSlabsCompatResourceInitializer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface DataResourcesInitializer extends PackResourcesInitializer<DataResourcesInitializer> {
    @NotNull("DataResourcesInitializer is null")
    DataResourcesInitializer setCustreRecipe(
            @NotNull("ResourceLocation provided was null") ResourceLocation id,
            @NotNull("CustreRecipeResourceInitializer consumer was null") Consumer<CustreRecipeResourceInitializer> consumer
    );

    @NotNull("DataResourcesInitializer is null")
    DataResourcesInitializer setKleeSlabsCompatibility(
            @NotNull("ResourceLocation provided was null") ResourceLocation id,
            @NotNull("CustreRecipeResourceInitializer consumer was null") Consumer<KleeSlabsCompatResourceInitializer> consumer
    );
}
