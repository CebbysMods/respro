package lv.cebbys.mcmods.respro.component.resource.blockstates.variant;

import lv.cebbys.mcmods.respro.component.resource.blockstates.BlockStateResource;
import lv.cebbys.mcmods.respro.mapper.JsonPart;
import lv.cebbys.mcmods.respro.mapper.ResourceExtractor;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class VariantStateResource extends BlockStateResource {
    @JsonPart(value = "variants")
    protected Map<String, List<VariantResource>> variants;

    public VariantStateResource variants(Consumer<VariantWrapper> consumer) {
        if(variants == null) {
            VariantWrapper wrapper = new VariantWrapper();
            consumer.accept(wrapper);
            variants = ResourceExtractor.data(wrapper);
        } else {
//            ResproLogger.warn("Variants have been already initialized");
        }
        return this;
    }

    public VariantStateResource variants(VariantWrapper consumer) {
        if(variants == null) {
            variants = ResourceExtractor.data(consumer);
        } else {
//            ResproLogger.warn("Variants have been already initialized");
        }
        return this;
    }

}
