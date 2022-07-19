package lv.cebbys.mcmods.respro.api.factory.profile;

import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public interface PackProfileSupplier<T extends ResourcePackProfile> extends ResourcePackProvider {
    Identifier getSupplierId();

    T getPackProfile();

    @Override
    default void register(Consumer<ResourcePackProfile> profileAdder, ResourcePackProfile.Factory factory) {
        profileAdder.accept(getPackProfile());
    }
}
