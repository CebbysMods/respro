package lv.cebbys.mcmods.respro.mixin;

import com.google.common.collect.ImmutableSet;
import lv.cebbys.mcmods.respro.Respro;
import lv.cebbys.mcmods.respro.core.ProfileSuppliers;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.resource.ClientBuiltinResourcePackProvider;
import net.minecraft.resource.ResourcePackManager;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Arrays;

@Mixin(ResourcePackManager.class)
public class ResourcePackManagerMixin {
    private static final Logger LOGGER = LoggerFactory.getLogger(Respro.class);

    @Redirect(method = "<init>*", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableSet;copyOf([Ljava/lang/Object;)Lcom/google/common/collect/ImmutableSet;"))
    private <E> ImmutableSet<Object> appendResourcePackSuppliers(E[] elements) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            LOGGER.info("Appending data pack profile providers for server");
            return appendDataProfileSuppliers(elements);
        }
        if (isForClient(elements)) {
            LOGGER.info("Appending asset pack profile providers for client");
            return appendAssetProfileSuppliers(elements);
        } else {
            LOGGER.info("Appending data pack profile providers for client");
            return appendDataProfileSuppliers(elements);
        }
    }

    private <E> boolean isForClient(E[] elements) {
        return Arrays.stream(elements).anyMatch(element -> element instanceof ClientBuiltinResourcePackProvider);
    }

    private <E> ImmutableSet<Object> appendDataProfileSuppliers(E[] elements) {
        Object[] factories = ProfileSuppliers.DATA_PROFILE_SUPPLIERS.stream().toArray();
        return ImmutableSet.copyOf(ArrayUtils.addAll(elements, factories));
    }

    private <E> ImmutableSet<Object> appendAssetProfileSuppliers(E[] elements) {
        Object[] factories = ProfileSuppliers.ASSET_PROFILE_SUPPLIERS.stream().toArray();
        return ImmutableSet.copyOf(ArrayUtils.addAll(elements, factories));
    }
}
