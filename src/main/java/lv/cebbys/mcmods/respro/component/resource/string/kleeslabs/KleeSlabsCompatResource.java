package lv.cebbys.mcmods.respro.component.resource.string.kleeslabs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lv.cebbys.mcmods.respro.api.initializer.kleeslabs.KleeSlabsCompatResourceInitializer;
import lv.cebbys.mcmods.respro.component.core.maybe.Maybe;
import lv.cebbys.mcmods.respro.component.mapper.JsonPart;
import lv.cebbys.mcmods.respro.component.resource.AbstractJsonObjectResource;
import lv.cebbys.mcmods.respro.exception.ResourceValidationException;
import net.minecraft.core.Registry;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.SlabBlock;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public final class KleeSlabsCompatResource extends AbstractJsonObjectResource implements KleeSlabsCompatResourceInitializer {
    @JsonPart("modid")
    private String modid;
    @JsonPart("converter")
    private String converter;
    @JsonPart("slabs")
    private final List<String> slabs = new ArrayList<>();

    @Override
    public boolean belongsTo(@NotNull PackType type) {
        return PackType.SERVER_DATA.equals(type);
    }

    @Override
    public void validate() throws ResourceValidationException {
        if (modid == null || "".equals(modid))
            throw new ResourceValidationException("KleeSlabsCompatResource 'modid' property has not been initialized");
        if (converter == null || "".equals(converter))
            throw new ResourceValidationException("KleeSlabsCompatResource 'converter' property has not been initialized");
        if (slabs.isEmpty()) throw new ResourceValidationException("KleeSlabsCompatResource 'slabs' property is empty");
    }

    @Override
    public @NotNull("KleeSlabsCompatResourceInitializer initializer is null") KleeSlabsCompatResourceInitializer setModid(@NotNull("Provided mod id is null") String modid) {
        this.modid = modid;
        return this;
    }

    @Override
    public @NotNull("KleeSlabsCompatResourceInitializer initializer is null") KleeSlabsCompatResourceInitializer setConverter(@NotNull("Provided slab converter is null") String converter) {
        this.converter = converter;
        return this;
    }

    @Override
    public @NotNull("KleeSlabsCompatResourceInitializer initializer is null") KleeSlabsCompatResourceInitializer setSlab(@NotNull("Provided slab id is null") String slab) {
        if (!slabs.contains(slab)) slabs.add(slab);
        return this;
    }

    @Override
    public @NotNull("KleeSlabsCompatResourceInitializer initializer is null") KleeSlabsCompatResourceInitializer setSlab(@NotNull("Provided slab id is null") SlabBlock slab) {
        new Maybe<>(slab)
                .transform(Registry.BLOCK::getKey)
                .ifFalse(Registry.BLOCK.getDefaultKey()::equals)
                .manipulate(this::setSlab);
        return this;
    }
}
