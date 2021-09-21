package lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.blockstates.multipart;

import lv.cebbys.mcmods.celib.respro.imp.loggers.ResproLogger;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.parser.JsonPart;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.JsonResource;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.blockstates.variant.VariantResource;
import lv.cebbys.mcmods.celib.respro.imp.utilities.BlockProperty;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CaseResource extends JsonResource {

    @JsonPart(path = "when")
    protected Map<String, String> when;
    @JsonPart(path = "when/or")
    protected List<Map<String, String>> whenOr;
    @JsonPart(path = "apply")
    protected VariantResource apply;

    public CaseResource when(BlockProperty o) {
        if(when == null && whenOr == null) {
            when = o.toMultipart();
        } else {
            ResproLogger.fail("Reinitialization is not allowed for " + getClass().getSimpleName() + " when element");
        }
        return this;
    }

    public CaseResource when(List<BlockProperty> o) {
        if(when == null && whenOr == null) {
            whenOr = o.stream().map(BlockProperty::toMultipart).collect(Collectors.toList());
        } else {
            ResproLogger.fail("Reinitialization is not allowed for " + getClass().getSimpleName() + " when or element");
        }
        return this;
    }

    public CaseResource apply(VariantResource o) {
        if(apply == null) {
            apply = o;
        } else {
            ResproLogger.fail("Reinitialization is not allowed for " + getClass().getSimpleName() + " apply element");
        }
        return this;
    }
}
