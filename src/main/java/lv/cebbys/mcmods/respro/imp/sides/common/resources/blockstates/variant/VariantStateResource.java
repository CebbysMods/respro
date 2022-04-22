package lv.cebbys.mcmods.respro.imp.sides.common.resources.blockstates.variant;

import lv.cebbys.mcmods.respro.imp.loggers.ResproLogger;
import lv.cebbys.mcmods.respro.imp.parser.JsonPart;
import lv.cebbys.mcmods.respro.imp.sides.common.resources.blockstates.BlockStateResource;
import lv.cebbys.mcmods.respro.imp.parser.ResourceExtractor;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class VariantStateResource extends BlockStateResource {
    @JsonPart(path = "variants")
    protected Map<String, List<VariantResource>> variants;

    public VariantStateResource variants(Consumer<VariantWrapper> consumer) {
        if(variants == null) {
            VariantWrapper wrapper = new VariantWrapper();
            consumer.accept(wrapper);
            variants = ResourceExtractor.data(wrapper);
        } else {
            ResproLogger.warn("Variants have been already initialized");
        }
        return this;
    }

    public VariantStateResource variants(VariantWrapper consumer) {
        if(variants == null) {
            variants = ResourceExtractor.data(consumer);
        } else {
            ResproLogger.warn("Variants have been already initialized");
        }
        return this;
    }

}
