package lv.cebbys.mcmods.respro.api.pack;

import lombok.Getter;
import lv.cebbys.mcmods.respro.component.resource.pack.profile.PackProfileResource;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@Getter
public class Pack extends net.minecraft.server.packs.repository.Pack {
    private final ResourceLocation location;

    public Pack(
            @NotNull ResourceLocation packLocation,
            @NotNull PackProfileResource packProfile,
            @NotNull Supplier<PackResources> supplier,
            @NotNull PackType packType
    ) {
        super(
                packLocation.toString(),
                packProfile.isAlwaysEnabled(),
                supplier,
                Component.literal(packProfile.getName().getAsString()),
                Component.literal(packProfile.getMeta().getDescription()),
                PackCompatibility.forFormat(packProfile.getMeta().getFormat(), packType),
                packProfile.getPosition(),
                packProfile.isPinned(),
                PackSource.decorating(packProfile.getSource().getAsString())
        );
        location = packLocation;
    }
}
