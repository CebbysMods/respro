package lv.cebbys.mcmods.respro.component.resource.string.custre;

import lombok.Getter;
import lv.cebbys.mcmods.respro.api.initializer.custre.CustreRecipeResourceInitializer;
import lv.cebbys.mcmods.respro.component.mapper.JsonPart;
import lv.cebbys.mcmods.respro.component.resource.core.AbstractJsonObjectResource;
import lv.cebbys.mcmods.respro.exception.ResourceValidationException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;

@Getter
public class CustreRecipeResource extends AbstractJsonObjectResource implements CustreRecipeResourceInitializer {
    @JsonPart(value = "ingredient")
    protected ResourceLocation ingredient;
    @JsonPart(value = "result")
    protected ResourceLocation result;

    @Override
    public @NotNull CustreRecipeResourceInitializer setIngredient(@NotNull ResourceLocation input) {
        ingredient = input;
        return this;
    }

    @Override
    public @NotNull CustreRecipeResourceInitializer setResult(@NotNull ResourceLocation output) {
        result = output;
        return this;
    }

    @Override
    public boolean belongsTo(@NotNull("Provided PackType is null") PackType type) {
        return PackType.SERVER_DATA.equals(type);
    }

    @Override
    public void validate() throws ResourceValidationException {

    }
}
