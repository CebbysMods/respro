package lv.cebbys.mcmods.celib.respro.imp.builders;

import lv.cebbys.mcmods.celib.respro.imp.builders.AssetPackBuilder;
import lv.cebbys.mcmods.celib.respro.imp.builders.DataPackBuilder;
import lv.cebbys.mcmods.celib.respro.imp.packs.ResproAssetPack;
import lv.cebbys.mcmods.celib.respro.imp.packs.ResproDataPack;
import net.minecraft.util.Identifier;

public abstract class DataPackExtractor extends DataPackBuilder {
    private DataPackExtractor(Identifier id) {
        super(id);
    }

    public static ResproDataPack from(DataPackBuilder builder) {
        return new ResproDataPack(builder.packId, builder.packName, builder.resources);
    }
}
