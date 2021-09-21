package lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.blockstates.multipart;

import lv.cebbys.mcmods.celib.respro.imp.loggers.ResproLogger;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.blockstates.variant.VariantResource;
import lv.cebbys.mcmods.celib.respro.imp.utilities.BlockProperty;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class WhenCase {

    private final CaseResource caseResource;
    private final List<BlockProperty> properties = new LinkedList<>();

    public WhenCase(CaseResource c, BlockProperty p0) {
        caseResource = c;
        properties.add(p0);
    }

    public void apply(Consumer<VariantResource> consumer) {
        VariantResource variant = new VariantResource();
        consumer.accept(variant);
        if(properties.size() == 1) {
            caseResource.when(properties.get(0));
        } else if(properties.size() > 1) {
            caseResource.when(properties);
        } else {
            ResproLogger.fail("Impossible condition, properties size cant be equal to 0");
        }
        caseResource.apply(variant);
    }

    public WhenCase or(BlockProperty p0) {
        properties.add(p0);
        return this;
    }
}
