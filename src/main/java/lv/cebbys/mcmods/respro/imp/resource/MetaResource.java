package lv.cebbys.mcmods.respro.imp.resource;

import lv.cebbys.mcmods.respro.imp.parser.JsonPart;
import net.minecraft.resource.ResourceType;

public class MetaResource extends JsonObjectResource {
    @JsonPart(path = "pack/description")
    private String description;
    @JsonPart(path = "pack/pack_format")
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
