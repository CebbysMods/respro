package lv.cebbys.mcmods.celib.respro.imp.sides.client.builders;

import lv.cebbys.mcmods.celib.respro.imp.sides.client.packs.ResproAssetPack;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.builders.impl.ResourcePackExtractor;
import net.minecraft.util.Identifier;

public abstract class AssetPackExtractor extends AssetPackBuilder {
    protected AssetPackExtractor(Identifier id) {
        super(id);
    }

    public static ResproAssetPack from(AssetPackBuilder builder) {
        return new ResproAssetPack(
                ResourcePackExtractor.getPackId(builder),
                ResourcePackExtractor.getPackName(builder),
                ResourcePackExtractor.getPackResources(builder));
    }
}
