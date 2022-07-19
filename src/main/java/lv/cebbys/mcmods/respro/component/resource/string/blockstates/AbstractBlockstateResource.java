package lv.cebbys.mcmods.respro.component.resource.string.blockstates;

import lv.cebbys.mcmods.respro.component.resource.core.AbstractJsonObjectResource;
import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractBlockstateResource extends AbstractJsonObjectResource {
    @Override
    public boolean belongsTo(@NotNull PackType type) {
        return PackType.CLIENT_RESOURCES.equals(type);
    }
}
