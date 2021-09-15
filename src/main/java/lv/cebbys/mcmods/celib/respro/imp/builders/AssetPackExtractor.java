package lv.cebbys.mcmods.celib.respro.imp.builders;

import lv.cebbys.mcmods.celib.respro.imp.builders.AssetPackBuilder;
import lv.cebbys.mcmods.celib.respro.imp.builders.ResourcePackBuilder;
import lv.cebbys.mcmods.celib.respro.imp.packs.ResproAssetPack;
import net.minecraft.util.Identifier;

public abstract class AssetPackExtractor extends AssetPackBuilder {
    protected AssetPackExtractor(Identifier id) {
        super(id);
    }

    public static ResproAssetPack from(AssetPackBuilder builder) {
        return new ResproAssetPack(builder.packId, builder.packName, builder.resources);
    }
}
