package lv.cebbys.mcmods.respro.api.pack;

import lv.cebbys.mcmods.respro.component.resource.pack.profile.PackProfileResource;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class Assets extends Pack {
    public Assets(@NotNull ResourceLocation packLocation, @NotNull PackProfileResource packProfile, @NotNull Supplier<PackResources> supplier) {
        super(packLocation, packProfile, supplier, PackType.CLIENT_RESOURCES);
    }
}
