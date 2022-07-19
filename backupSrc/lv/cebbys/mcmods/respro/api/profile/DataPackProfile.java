package lv.cebbys.mcmods.respro.api.pack;

import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackCompatibility;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackSource;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.metadata.PackResourceMetadata;
import net.minecraft.text.Text;

import java.util.function.Supplier;

public class DataPackProfile extends ResourcePackProfile {
    public DataPackProfile(String name, boolean alwaysEnabled, Supplier<ResourcePack> packFactory, Text displayName, Text description, ResourcePackCompatibility compatibility, InsertionPosition direction, boolean pinned, ResourcePackSource source) {
        super(name, alwaysEnabled, packFactory, displayName, description, compatibility, direction, pinned, source);
    }

    public DataPackProfile(String name, Text displayName, boolean alwaysEnabled, Supplier<ResourcePack> packFactory, PackResourceMetadata metadata, ResourceType type, InsertionPosition direction, ResourcePackSource source) {
        super(name, displayName, alwaysEnabled, packFactory, metadata, type, direction, source);
    }
}
