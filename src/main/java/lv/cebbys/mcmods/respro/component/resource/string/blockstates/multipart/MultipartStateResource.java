package lv.cebbys.mcmods.respro.component.resource.string.blockstates.multipart;

import lv.cebbys.mcmods.respro.component.mapper.JsonPart;

import java.util.LinkedList;
import java.util.List;

public class MultipartStateResource {
    @JsonPart(value = "multipart")
    protected final List<CaseResource> multipart = new LinkedList<>();

//    public void apply(Consumer<VariantResource> consumer) {
//        CaseResource resource = new CaseResource();
//        VariantResource variant = new VariantResource();
//        consumer.accept(variant);
//        resource.apply(variant);
//        multipart.add(resource);
//    }

//    public WhenCase when(BlockProperty p0) {
//        CaseResource resource = new CaseResource();
//        multipart.add(resource);
//        return new WhenCase(resource, p0);
//    }
//
//    @Override
//    public void validate() throws ResourceValidationException {
//
//    }
}
