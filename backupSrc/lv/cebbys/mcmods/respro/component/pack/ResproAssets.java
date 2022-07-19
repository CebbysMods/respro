package lv.cebbys.mcmods.respro.component.pack;

import lv.cebbys.mcmods.respro.component.resource.Resource;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resource.ResourcePackSource;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class ResproAssets extends ResproPack {
    protected ResproAssets(@NotNull Identifier packId, @NotNull String packName, @NotNull Map<Identifier, Resource> packResources, @NotNull ResourcePackSource packSource, boolean packAlwaysEnabled) {
        super(packId, packName, packResources, packSource, packAlwaysEnabled);
    }
}
