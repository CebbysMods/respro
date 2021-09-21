package lv.cebbys.mcmods.celib.respro.imp.sides.common.providers;

import lv.cebbys.mcmods.celib.respro.imp.loggers.ResproLogger;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.packs.ResproResourcePack;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;

import java.util.function.Consumer;

public abstract class ResproPackProvider<T extends ResproResourcePack> implements ResourcePackProvider {

    public abstract MutableRegistry<T> getResourceRegistry();

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
