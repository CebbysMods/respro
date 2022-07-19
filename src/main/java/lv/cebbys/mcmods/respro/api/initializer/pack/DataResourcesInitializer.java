package lv.cebbys.mcmods.respro.api.initializer.pack;

import lv.cebbys.mcmods.respro.api.initializer.custre.CustreRecipeResourceInitializer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface DataResourcesInitializer extends PackResourcesInitializer<DataResourcesInitializer> {
    @NotNull DataResourcesInitializer setCustreRecipe(@NotNull ResourceLocation id, @NotNull Consumer<CustreRecipeResourceInitializer> consumer);
}
