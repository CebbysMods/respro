package lv.cebbys.mcmods.respro.component.resource.string.items;

import lv.cebbys.mcmods.respro.component.mapper.JsonPart;
import lv.cebbys.mcmods.respro.component.mapper.ResourceExtractor;
import lv.cebbys.mcmods.respro.component.resource.core.AbstractJsonObjectResource;
import lv.cebbys.mcmods.respro.exception.ResourceValidationException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Consumer;

public class ItemModelResource extends AbstractJsonObjectResource {
    @JsonPart(value = "parent")
    private String parent;
    @JsonPart(value = "display")
    private Map<String, DisplayElementResource> display;

    public ItemModelResource display(Consumer<DisplayElementWrapper> named) {
        if (display == null) {
            DisplayElementWrapper wrapper = new DisplayElementWrapper();
            named.accept(wrapper);
            display = ResourceExtractor.data(wrapper);
        } else {
//            ResproLogger.warn("Item model display element has been already created");
        }
        return this;
    }

    public ItemModelResource parent(ResourceLocation id) {
        if (parent != null) {
//            ResproLogger.warn("Item model parent has been already initialized");
        } else {
            parent = id.toString();
        }
        return this;
    }

    @Override
    public boolean belongsTo(@NotNull PackType type) {
        return PackType.CLIENT_RESOURCES.equals(type);
    }

    @Override
    public void validate() throws ResourceValidationException {

    }
}
