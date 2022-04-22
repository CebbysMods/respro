package lv.cebbys.mcmods.respro.imp.sides.common.resources.blockstates.variant;

import lv.cebbys.mcmods.respro.imp.component.BlockProperty;
import lv.cebbys.mcmods.respro.imp.parser.ResourceCollectionExtractor;
import lv.cebbys.mcmods.respro.imp.parser.ResourceCollector;
import lv.cebbys.mcmods.respro.imp.parser.ResourceWrapper;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class VariantWrapper extends ResourceWrapper<String, List<VariantResource>> {

    public void variant(BlockProperty tags, Consumer<VariantResource> consumer) {
        VariantResource resource = new VariantResource();
        consumer.accept(resource);
        variants(tags, Collections.singletonList(resource));
    }

    public void variant(Consumer<VariantResource> consumer) {
        variant(new BlockProperty(), consumer);
    }

    public void variants(BlockProperty tags, Consumer<ResourceCollector<VariantResource>> consumer) {
        ResourceCollector<VariantResource> collector = new ResourceCollector<>(VariantResource.class);
        consumer.accept(collector);
        List<VariantResource> list = ResourceCollectionExtractor.collect(collector);
        variants(tags, list);
    }

    public void variants(Consumer<ResourceCollector<VariantResource>> consumer) {
        variants(new BlockProperty(), consumer);
    }

    protected void variants(BlockProperty tags, List<VariantResource> resources) {
        if (tags.size() < 1) {
            variants("", resources);
        } else {
            variants(tags.toString(), resources);
        }
    }

    protected void variants(String tags, List<VariantResource> resources) {
        this.resources(tags, resources);
    }

    @Override
    protected Class<?> wrappedClass() {
        return VariantResource.class;
    }
}
