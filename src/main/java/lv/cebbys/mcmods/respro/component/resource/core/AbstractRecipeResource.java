package lv.cebbys.mcmods.respro.component.resource.core;

import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractRecipeResource extends AbstractJsonObjectResource {

    @Override
    public boolean belongsTo(@NotNull PackType type) {
        return PackType.SERVER_DATA.equals(type);
    }
}
