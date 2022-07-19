package lv.cebbys.mcmods.respro.imp.builders.assetpack;

import lv.cebbys.mcmods.respro.imp.builders.resourcepack.ResourcePackExtractor;
import lv.cebbys.mcmods.respro.imp.sides.client.packs.ResproAssetPack;
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
