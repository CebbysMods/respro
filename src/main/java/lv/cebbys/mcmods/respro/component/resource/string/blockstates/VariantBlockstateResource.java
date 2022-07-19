package lv.cebbys.mcmods.respro.component.resource.string.blockstates;

import lombok.Getter;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.BlockVariantPropertyResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.BlockVariantResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.VariantBlockstateResourceInitializer;
import lv.cebbys.mcmods.respro.component.mapper.JsonPart;
import lv.cebbys.mcmods.respro.exception.ResourceValidationException;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Getter
public class VariantBlockstateResource extends AbstractBlockstateResource implements VariantBlockstateResourceInitializer {
    @JsonPart("variants")
    private final Map<String, BlockVariantResource> variants = new HashMap<>();

    @Override
    public @NotNull VariantBlockstateResourceInitializer setVariant(
            @NotNull Consumer<BlockVariantPropertyResourceInitializer> propertyConsumer,
            @NotNull Consumer<BlockVariantResourceInitializer> variantConsumer
    ) {
        BlockVariantPropertyResource properties = new BlockVariantPropertyResource();
        propertyConsumer.accept(properties);
        BlockVariantResource variant = new BlockVariantResource();
        variantConsumer.accept(variant);
        variants.put(properties.getAsString(), variant);
        return this;
    }

    @Override
    public void validate() throws ResourceValidationException {

    }
}
