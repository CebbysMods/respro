package lv.cebbys.mcmods.respro.component.builder;

import lv.cebbys.mcmods.respro.api.builder.pack.AssetBuilder;
import lv.cebbys.mcmods.respro.api.factory.profile.AssetProfileSupplier;

public class ResproAssetBuilder extends ResproPackBuilder<AssetProfileSupplier> implements AssetBuilder {
    public ResproAssetBuilder() {
    }

    @Override
    public AssetBuilder setPackIcon(String pathInResources) {
        return null;
    }

    @Override
    public AssetBuilder setPackName(String packName) {
        return null;
    }

    @Override
    public AssetBuilder setPackDescription(String description) {
        return null;
    }

    @Override
    public AssetProfileSupplier getSupplier() {
        return null;
    }
}
