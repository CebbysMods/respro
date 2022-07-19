package lv.cebbys.mcmods.respro.component.resource;

import net.minecraft.resource.ResourceType;

public abstract class RecipeResource extends JsonObjectResource {

    @Override
    protected boolean belongsTo(ResourceType type) {
        return ResourceType.SERVER_DATA.equals(type);
    }
}
