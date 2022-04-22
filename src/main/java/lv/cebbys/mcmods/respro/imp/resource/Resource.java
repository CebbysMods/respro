package lv.cebbys.mcmods.respro.imp.resource;

import net.minecraft.resource.ResourceType;

import java.io.InputStream;

public abstract class Resource {
    protected abstract InputStream getAsStream();

    protected abstract boolean belongsTo(ResourceType type);
}
