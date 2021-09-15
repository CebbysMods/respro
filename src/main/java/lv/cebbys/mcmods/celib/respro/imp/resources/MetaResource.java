package lv.cebbys.mcmods.celib.respro.imp.resources;

import lv.cebbys.mcmods.celib.respro.imp.parser.JsonPart;
import net.minecraft.resource.ResourceType;

public class MetaResource extends JsonResource {
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
