package lv.cebbys.mcmods.celib.respro.imp.providers.pack;

import lv.cebbys.mcmods.celib.respro.api.DataPackProvider;
import lv.cebbys.mcmods.celib.respro.imp.ResourceRegistry;
import lv.cebbys.mcmods.celib.respro.imp.packs.ResproResourcePack;
import net.minecraft.util.registry.MutableRegistry;

public class ResproDataProvider extends ResproPackProvider implements DataPackProvider {
    @Override
    public MutableRegistry<ResproResourcePack> getResourceRegistry() {
        return ResourceRegistry.SERVER_RESOURCE_PACKS;
    }
}
