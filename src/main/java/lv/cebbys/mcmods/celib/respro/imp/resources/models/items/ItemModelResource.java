package lv.cebbys.mcmods.celib.respro.imp.resources.models.items;

import lv.cebbys.mcmods.celib.respro.imp.loggers.ResproLogger;
import lv.cebbys.mcmods.celib.respro.imp.parser.JsonPart;
import lv.cebbys.mcmods.celib.respro.imp.resources.JsonResource;
import lv.cebbys.mcmods.celib.respro.imp.utilities.ResourceExtractor;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.function.Consumer;

public class ItemModelResource extends JsonResource {
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
        if(parent != null) {
            ResproLogger.warn("Item model parent has been already initialized");
        } else {
            parent = id.toString();
        }
        return this;
    }
}
