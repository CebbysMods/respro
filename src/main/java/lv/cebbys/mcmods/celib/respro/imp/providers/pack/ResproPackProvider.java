package lv.cebbys.mcmods.celib.respro.imp.providers.pack;

import lv.cebbys.mcmods.celib.respro.imp.loggers.ResproLogger;
import lv.cebbys.mcmods.celib.respro.imp.packs.ResproResourcePack;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;

import java.util.function.Consumer;

public abstract class ResproPackProvider implements ResourcePackProvider {

    public abstract MutableRegistry<ResproResourcePack> getResourceRegistry();

    @Override
    public void register(Consumer<ResourcePackProfile> profileAdder, ResourcePackProfile.Factory factory) {
        for (Identifier id : getResourceRegistry().getIds()) {
            ResproLogger.debug("Appending resource pack: " + id);
            ResproResourcePack pack = getResourceRegistry().get(id);
            if(pack != null) {
                ResproLogger.debug("Appendable resource pack: " + pack);
                ResourcePackProfile profile = pack.getResourcePack(factory);
                if(profile != null) {
                    ResproLogger.debug("Appendable pack profile: " + profile);
                    profileAdder.accept(profile);
                } else {
                    ResproLogger.warn("Failed to retrieve valid resource pack profile");
                }
            } else {
                ResproLogger.warn("Failed to retrieve valid resource pack");
            }
        }
    }
}
