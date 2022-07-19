package lv.cebbys.mcmods.respro.api.initializer.custre;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public interface CustreRecipeResourceInitializer {
    @NotNull CustreRecipeResourceInitializer setIngredient(@NotNull ResourceLocation input);

    @NotNull CustreRecipeResourceInitializer setResult(@NotNull ResourceLocation output);
}
