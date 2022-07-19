package lv.cebbys.mcmods.respro.mixin;

import com.google.common.collect.ImmutableSet;
import lv.cebbys.mcmods.respro.Respro;
import lv.cebbys.mcmods.respro.api.pack.Assets;
import lv.cebbys.mcmods.respro.api.pack.Data;
import lv.cebbys.mcmods.respro.api.supplier.AssetListSupplier;
import lv.cebbys.mcmods.respro.api.supplier.DataListSupplier;
import lv.cebbys.mcmods.respro.api.supplier.PackSupplier;
import lv.cebbys.mcmods.respro.component.core.PackSuppliers;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.resources.ClientPackSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.repository.PackRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Arrays;
import java.util.List;

import static lv.cebbys.mcmods.respro.constant.ResproConstants.RESPRO;

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

    private <E> boolean isForClient(E[] elements) {
        return Arrays.stream(elements).anyMatch(element -> element instanceof ClientPackSource);
    }

    private <E> ImmutableSet<Object> appendDataProfileSupplier(E[] elements) {
        return ImmutableSet.copyOf(ArrayUtils.add(elements, dataSupplier));
    }

    private <E> ImmutableSet<Object> appendAssetProfileSupplier(E[] elements) {
        return ImmutableSet.copyOf(ArrayUtils.add(elements, assetSupplier));
    }

    private final AssetListSupplier assetSupplier = new AssetListSupplier() {
        private final ResourceLocation id = new ResourceLocation(RESPRO, "respro_asset_supplier");

        @Override
        public @NotNull ResourceLocation getSupplierId() {
            return id;
        }

        @Override
        public @NotNull List<Assets> getPacks() {
            return PackSuppliers.ASSET_PROFILE_SUPPLIERS.stream()
                    .map(PackSupplier::getPack)
                    .toList();
        }
    };

    private final DataListSupplier dataSupplier = new DataListSupplier() {
        private final ResourceLocation id = new ResourceLocation(RESPRO, "respro_data_supplier");

        @Override
        public @NotNull ResourceLocation getSupplierId() {
            return id;
        }

        @Override
        public @NotNull List<Data> getPacks() {
            return PackSuppliers.DATA_PROFILE_SUPPLIERS.stream()
                    .map(PackSupplier::getPack)
                    .toList();
        }
    };
}
