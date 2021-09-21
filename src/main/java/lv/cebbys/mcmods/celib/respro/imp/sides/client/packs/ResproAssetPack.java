package lv.cebbys.mcmods.celib.respro.imp.sides.client.packs;

import lv.cebbys.mcmods.celib.respro.imp.sides.common.packs.ResproResourcePack;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.Resource;
import net.minecraft.util.Identifier;

import java.util.Map;

public class ResproAssetPack extends ResproResourcePack {
    public ResproAssetPack(Identifier packId, String packName, Map<Identifier, Resource> res) {
        super(packId, packName, res);
    }
}
