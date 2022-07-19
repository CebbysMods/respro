package lv.cebbys.mcmods.respro.imp.sides.client.packs;

import lv.cebbys.mcmods.respro.component.resource.Resource;
import lv.cebbys.mcmods.respro.imp.sides.common.packs.ResproResourcePack;
import net.minecraft.util.Identifier;

import java.util.Map;

public class ResproAssetPack extends ResproResourcePack {
    public ResproAssetPack(Identifier packId, String packName, Map<Identifier, Resource> res) {
        super(packId, packName, res);
    }
}
