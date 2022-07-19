package lv.cebbys.mcmods.respro.component.builder;

import lv.cebbys.mcmods.respro.api.builder.pack.DataBuilder;
import lv.cebbys.mcmods.respro.api.factory.profile.DataProfileSupplier;

public class ResproDataBuilder extends ResproPackBuilder<DataProfileSupplier> implements DataBuilder {
    public ResproDataBuilder() {
    }

    @Override
    public DataBuilder setPackIcon(String pathInResources) {
        return null;
    }

    @Override
    public DataBuilder setPackName(String packName) {
        return null;
    }

    @Override
    public DataBuilder setPackDescription(String description) {
        return null;
    }

    @Override
    public DataProfileSupplier getSupplier() {
        return null;
    }
}
