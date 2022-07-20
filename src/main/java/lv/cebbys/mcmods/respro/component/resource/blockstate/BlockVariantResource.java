package lv.cebbys.mcmods.respro.component.resource.blockstate;

import lv.cebbys.mcmods.respro.api.initializer.blockstate.BlockVariantResourceInitializer;
import lv.cebbys.mcmods.respro.component.mapper.JsonPart;
import lv.cebbys.mcmods.respro.component.resource.AbstractJsonObjectResource;
import lv.cebbys.mcmods.respro.exception.ResourceValidationException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;

public class BlockVariantResource extends AbstractJsonObjectResource implements BlockVariantResourceInitializer {
    @JsonPart(value = "model")
    protected String model;
    @JsonPart(value = "x")
    protected Integer x;
    @JsonPart(value = "y")
    protected Integer y;
    @JsonPart(value = "uvlock")
    protected Boolean uvlock;
    @JsonPart(value = "weight")
    protected Integer weight;

    @Override
    public void validate() throws ResourceValidationException {
        if (model == null) throw new ResourceValidationException("BlockVariantResourceBuilder model property is null");
    }

    @Override
    public @NotNull BlockVariantResourceInitializer setModel(@NotNull String modelLocation) {
        model = modelLocation;
        return this;
    }

    @Override
    public @NotNull BlockVariantResourceInitializer setModel(@NotNull ResourceLocation model) {
        return setModel(model.toString());
    }

    @Override
    public @NotNull BlockVariantResourceInitializer setX(int value) {
        x = value;
        return this;
    }

    @Override
    public @NotNull BlockVariantResourceInitializer setY(int value) {
        y = value;
        return this;
    }

    @Override
    public @NotNull BlockVariantResourceInitializer setUVLock(boolean ul) {
        uvlock = ul;
        return this;
    }

    @Override
    public @NotNull BlockVariantResourceInitializer setWeight(int value) {
        weight = value;
        return this;
    }

    @Override
    public boolean belongsTo(@NotNull PackType type) {
        return PackType.CLIENT_RESOURCES.equals(type);
    }
}
