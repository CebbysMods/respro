package lv.cebbys.mcmods.respro.component.resource.items;

import lv.cebbys.mcmods.respro.component.resource.JsonObjectResource;
import lv.cebbys.mcmods.respro.mapper.JsonPart;
import lv.cebbys.mcmods.respro.mapper.ResourceExtractor;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.function.Consumer;

public class ItemModelResource extends JsonObjectResource {
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

    public ItemModelResource parent(Identifier id) {
        if (parent != null) {
//            ResproLogger.warn("Item model parent has been already initialized");
        } else {
            parent = id.toString();
        }
        return this;
    }

    @Override
    protected boolean belongsTo(ResourceType type) {
        return ResourceType.CLIENT_RESOURCES.equals(type);
    }
}
