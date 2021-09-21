package lv.cebbys.mcmods.celib.respro.imp.sides.common.resources;

import net.minecraft.resource.ResourceType;

public abstract class ImageResource extends Resource {
    @Override
    protected boolean belongsTo(ResourceType type) {
        return type.equals(ResourceType.CLIENT_RESOURCES);
    }
}
