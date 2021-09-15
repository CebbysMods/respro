package lv.cebbys.mcmods.celib.respro.imp.packs;

import lv.cebbys.mcmods.celib.respro.imp.resources.Resource;
import net.minecraft.util.Identifier;

import java.util.Map;

public class ResproDataPack extends ResproResourcePack {
    public ResproDataPack(Identifier packId, String packName, Map<Identifier, Resource> res) {
        super(packId, packName, res);
    }
}
