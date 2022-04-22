package lv.cebbys.mcmods.respro.imp.sides.common.resources.blockstates;

import lv.cebbys.mcmods.respro.imp.resource.JsonObjectResource;
import net.minecraft.resource.ResourceType;

public class BlockStateResource extends JsonObjectResource {

    @Override
    protected boolean belongsTo(ResourceType type) {
        return ResourceType.CLIENT_RESOURCES.equals(type);
    }
}
