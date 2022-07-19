package lv.cebbys.mcmods.respro.component.resource.string.kleeslabs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lv.cebbys.mcmods.respro.component.mapper.JsonPart;
import lv.cebbys.mcmods.respro.component.resource.core.AbstractJsonObjectResource;
import lv.cebbys.mcmods.respro.exception.ResourceValidationException;
import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@AllArgsConstructor
public final class KleeSlabsCompatResource extends AbstractJsonObjectResource {
    @JsonPart("modid")
    private final String modid;
    @JsonPart("converter")
    private final String converter;
    @JsonPart("slabs")
    private final List<String> slabs;

    @Override
    public boolean belongsTo(@NotNull PackType type) {
        return PackType.SERVER_DATA.equals(type);
    }

    @Override
    public void validate() throws ResourceValidationException {

    }
}
