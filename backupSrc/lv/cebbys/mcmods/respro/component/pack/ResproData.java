package lv.cebbys.mcmods.respro.component.pack;

import lv.cebbys.mcmods.respro.component.resource.Resource;
import net.minecraft.resource.ResourcePackSource;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ResproData extends ResproPack {
    protected ResproData(@NotNull Identifier packId, @NotNull String packName, @NotNull Map<Identifier, Resource> packResources, @NotNull ResourcePackSource packSource, boolean packAlwaysEnabled) {
        super(packId, packName, packResources, packSource, packAlwaysEnabled);
    }
}
