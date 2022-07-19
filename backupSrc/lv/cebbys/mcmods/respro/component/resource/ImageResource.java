package lv.cebbys.mcmods.respro.component.resource;

import net.minecraft.resource.ResourceType;

public abstract class ImageResource extends Resource {
    @Override
    protected boolean belongsTo(ResourceType type) {
        return type.equals(ResourceType.CLIENT_RESOURCES);
    }
}
