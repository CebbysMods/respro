package lv.cebbys.mcmods.respro.imp.sides.common.providers;

import lv.cebbys.mcmods.respro.api.DataPackProvider;
import lv.cebbys.mcmods.respro.imp.ResourceRegistry;
import lv.cebbys.mcmods.respro.imp.sides.common.packs.ResproDataPack;
import net.minecraft.util.registry.MutableRegistry;

public class ResproDataProvider extends ResproPackProvider<ResproDataPack> implements DataPackProvider {
    @Override
    public MutableRegistry<ResproDataPack> getResourceRegistry() {
        return ResourceRegistry.SERVER_RESOURCE_PACKS;
    }
}
