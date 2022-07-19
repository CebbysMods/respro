package lv.cebbys.mcmods.respro.imp.sides.common.packs;

import lv.cebbys.mcmods.respro.component.resource.Resource;
import net.minecraft.util.Identifier;

import java.util.Map;

public class ResproDataPack extends ResproResourcePack {
    public ResproDataPack(Identifier packId, String packName, Map<Identifier, Resource> res) {
        super(packId, packName, res);
    }
}
