package lv.cebbys.mcmods.respro.component.resource.core;

import lombok.Getter;
import lv.cebbys.mcmods.respro.api.initializer.core.MetaResourceInitializer;
import lv.cebbys.mcmods.respro.component.mapper.JsonPart;
import lv.cebbys.mcmods.respro.component.resource.AbstractJsonObjectResource;
import lv.cebbys.mcmods.respro.exception.ResourceValidationException;
import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;

@Getter
public final class MetaResource extends AbstractJsonObjectResource implements MetaResourceInitializer {
    @JsonPart(value = "pack/description")
    private String description;
    @JsonPart(value = "pack/pack_format")
    private int format;

    public MetaResource(@NotNull String packDescription, int packFormat) {
        description = packDescription;
        format = packFormat;
    }

    public MetaResource() {
        this("", 0);
    }

    @Override
    public void validate() throws ResourceValidationException {
        if (description == null) throw new ResourceValidationException("MetaResource description is null");
    }

    @Override
    public @NotNull MetaResourceInitializer setDescription(@NotNull String description) {
        this.description = description;
        return this;
    }

    @Override
    public @NotNull MetaResourceInitializer setFormat(int version) {
        format = version;
        return this;
    }

    @Override
    public boolean belongsTo(@NotNull PackType type) {
        return true;
    }
}
