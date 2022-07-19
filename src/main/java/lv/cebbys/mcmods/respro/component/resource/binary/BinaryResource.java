package lv.cebbys.mcmods.respro.component.resource.binary;

import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;

public abstract class BinaryResource {
    public abstract void validate();

    public abstract @NotNull InputStream getAsStream();

    public abstract boolean belongsTo(@NotNull PackType type);
}
