package lv.cebbys.mcmods.respro.component.resource.blockstates;

import lv.cebbys.mcmods.respro.component.resource.JsonObjectResource;
import net.minecraft.resource.ResourceType;

public class BlockStateResource extends JsonObjectResource {

    @Override
    protected boolean belongsTo(ResourceType type) {
        return ResourceType.CLIENT_RESOURCES.equals(type);
    }
}
