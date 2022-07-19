package lv.cebbys.mcmods.respro.imp.builders.datapack;

import lv.cebbys.mcmods.respro.imp.builders.resourcepack.ResourcePackExtractor;
import lv.cebbys.mcmods.respro.imp.sides.common.packs.ResproDataPack;
import net.minecraft.util.Identifier;

public abstract class DataPackExtractor extends DataPackBuilder {
    private DataPackExtractor(Identifier id) {
        super(id);
    }

    public static ResproDataPack from(DataPackBuilder builder) {
        return new ResproDataPack(
                ResourcePackExtractor.getPackId(builder),
                ResourcePackExtractor.getPackName(builder),
                ResourcePackExtractor.getPackResources(builder)
        );
    }
}
