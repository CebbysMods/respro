package lv.cebbys.mcmods.respro.component.resource;

import lv.cebbys.mcmods.respro.mapper.JsonPart;
import net.minecraft.resource.ResourceType;

public class MetaResource extends JsonObjectResource {
    @JsonPart(value = "pack/description")
    private String description;
    @JsonPart(value = "pack/pack_format")
    private Integer packFormat;

    public MetaResource description(String d) {
        description = d;
        return this;
    }

    public MetaResource packFormat(int i) {
        packFormat = i;
        return this;
    }

    @Override
    protected boolean belongsTo(ResourceType type) {
        return true;
    }
}
