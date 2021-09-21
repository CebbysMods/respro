package lv.cebbys.mcmods.celib.respro.imp.sides.common.builders;

import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.ImageResource;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.MetaResource;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.Resource;
import lv.cebbys.mcmods.celib.respro.imp.utilities.Factory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public interface ResourcePackBuilder {

    ImageResource setPackIcon(String pathToPng);
    ImageResource setPackIcon(Identifier pathToPng);

    MetaResource setPackDescription(Consumer<MetaResource> consumer);

    String setPackName(String packName);

    <T extends Resource> T addResource(Identifier resourcePath, Factory<T> resourceFactory);
    <T extends Resource> T addResource(Identifier resourcePath, Class<T> resourceType);
    <T extends Resource> T addResource(Identifier resourcePath, Class<T> resourceType, Consumer<T> consumer);
    <T extends Resource> T addResource(Identifier resourcePath, T resource);

}
