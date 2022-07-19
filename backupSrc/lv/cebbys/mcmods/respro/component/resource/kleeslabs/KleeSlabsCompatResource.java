package lv.cebbys.mcmods.respro.component.resource.kleeslabs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lv.cebbys.mcmods.respro.mapper.JsonPart;
import lv.cebbys.mcmods.respro.component.resource.JsonObjectResource;
import net.minecraft.resource.ResourceType;

import java.util.List;

@Getter
@AllArgsConstructor
public class KleeSlabsCompatResource extends JsonObjectResource {
    @JsonPart("modid")
    private final String modid;
    @JsonPart("converter")
    private final String converter;
    @JsonPart("slabs")
    private final List<String> slabs;
    
    @Override
    protected boolean belongsTo(ResourceType type) {
        return ResourceType.SERVER_DATA.equals(type);
    }
}
