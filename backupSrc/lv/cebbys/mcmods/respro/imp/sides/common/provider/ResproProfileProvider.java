package lv.cebbys.mcmods.respro.imp.sides.common.provider;

import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.registry.MutableRegistry;

import java.util.function.Consumer;

public abstract class ResproProfileProvider<T extends ResourcePackProvider> implements ResourcePackProvider {

    protected abstract MutableRegistry<T> getRegistry();

    @Override
    public void register(Consumer<ResourcePackProfile> profileAdder, ResourcePackProfile.Factory factory) {
        getRegistry().stream().forEachOrdered(profileProvider -> {
//            ResproLogger.debug("Appending resource profile provider: " + profileProvider);
            profileProvider.register(profileAdder, factory);
        });
    }
}
