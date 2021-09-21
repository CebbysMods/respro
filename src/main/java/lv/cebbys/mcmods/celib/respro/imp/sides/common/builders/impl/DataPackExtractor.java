package lv.cebbys.mcmods.celib.respro.imp.sides.common.builders.impl;

import lv.cebbys.mcmods.celib.respro.imp.sides.common.packs.ResproDataPack;
import net.minecraft.util.Identifier;

public abstract class DataPackExtractor extends DataPackBuilderImpl {
    private DataPackExtractor(Identifier id) {
        super(id);
    }

    public static ResproDataPack from(DataPackBuilderImpl builder) {
        return new ResproDataPack(builder.packId, builder.packName, builder.resources);
    }
}
