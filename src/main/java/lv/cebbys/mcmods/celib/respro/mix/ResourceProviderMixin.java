package lv.cebbys.mcmods.celib.respro.mix;

import com.google.common.collect.ImmutableSet;
import lv.cebbys.mcmods.celib.respro.imp.loggers.ResproLogger;
import lv.cebbys.mcmods.celib.respro.imp.providers.Providers;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.resource.ClientBuiltinResourcePackProvider;
import net.minecraft.resource.ResourcePackManager;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Arrays;

@Mixin(ResourcePackManager.class)
public class ResourceProviderMixin {

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableSet;copyOf([Ljava/lang/Object;)Lcom/google/common/collect/ImmutableSet;"))
    private <E> ImmutableSet<Object> appendCelibProviders(E[] elements) {
        Object provider;
        ResproLogger.debug("Appending resource profile provider");
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            ResproLogger.debug("Appending data pack provider to server");
            provider = Providers.DATA_PROFILE_PROVIDER;
        } else {
            boolean isForClient = Arrays.stream(elements)
                    .anyMatch(element -> element instanceof ClientBuiltinResourcePackProvider);
            if(isForClient) {
                ResproLogger.debug("Appending asset pack provider to client");
                provider = Providers.ASSET_PROFILE_PROVIDER;
            } else {
                ResproLogger.debug("Appending data pack provider to client");
                provider = Providers.DATA_PROFILE_PROVIDER;
            }
        }
        return ImmutableSet.copyOf(ArrayUtils.add(elements, provider));
    }
}
