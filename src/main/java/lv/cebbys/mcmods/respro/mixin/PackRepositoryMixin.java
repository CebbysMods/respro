package lv.cebbys.mcmods.respro.mixin;

import com.google.common.collect.ImmutableSet;
import lv.cebbys.mcmods.respro.Respro;
import lv.cebbys.mcmods.respro.component.core.PackSuppliers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.resources.ClientPackSource;
import net.minecraft.server.packs.repository.PackRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Arrays;

@Mixin(PackRepository.class)
public abstract class PackRepositoryMixin {
    private static final Logger LOGGER = LoggerFactory.getLogger(Respro.class);

    @Redirect(
            method = "<init>*",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/google/common/collect/ImmutableSet;copyOf([Ljava/lang/Object;)Lcom/google/common/collect/ImmutableSet;"
            )
    )
    private <E> ImmutableSet<Object> appendResourcePackSuppliers(E[] elements) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            LOGGER.info("Appending data pack profile providers for server");
            return appendDataProfileSupplier(elements);
        }
        if (isForClient(elements)) {
            LOGGER.info("Appending asset pack profile providers for client");
            return appendAssetProfileSupplier(elements);
        } else {
            LOGGER.info("Appending data pack profile providers for client");
            return appendDataProfileSupplier(elements);
        }
    }

    @Environment(EnvType.CLIENT)
    private <E> boolean isForClient(E[] elements) {
        return Arrays.stream(elements).anyMatch(element -> element instanceof ClientPackSource);
    }

    private <E> ImmutableSet<Object> appendDataProfileSupplier(E[] elements) {
        return ImmutableSet.copyOf(ArrayUtils.add(elements, PackSuppliers.RESPRO_DATA_SUPPLIER));
    }

    @Environment(EnvType.CLIENT)
    private <E> ImmutableSet<Object> appendAssetProfileSupplier(E[] elements) {
        return ImmutableSet.copyOf(ArrayUtils.add(elements, PackSuppliers.RESPRO_ASSET_SUPPLIER));
    }
}
