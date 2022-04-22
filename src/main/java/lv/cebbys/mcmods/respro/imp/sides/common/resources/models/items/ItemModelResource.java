package lv.cebbys.mcmods.respro.imp.sides.common.resources.models.items;

import lv.cebbys.mcmods.respro.imp.loggers.ResproLogger;
import lv.cebbys.mcmods.respro.imp.parser.ResourceExtractor;
import lv.cebbys.mcmods.respro.imp.resource.JsonObjectResource;
import lv.cebbys.mcmods.respro.imp.parser.JsonPart;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.function.Consumer;

public class ItemModelResource extends JsonObjectResource {
    @JsonPart(path = "parent")
    private String parent;
    @JsonPart(path = "display")
    private Map<String, DisplayElementResource> display;

    public ItemModelResource display(Consumer<DisplayElementWrapper> named) {
        if (display == null) {
            DisplayElementWrapper wrapper = new DisplayElementWrapper();
            named.accept(wrapper);
            display = ResourceExtractor.data(wrapper);
        } else {
            ResproLogger.warn("Item model display element has been already created");
        }
        return this;
    }

    public ItemModelResource parent(Identifier id) {
        if (parent != null) {
            ResproLogger.warn("Item model parent has been already initialized");
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
