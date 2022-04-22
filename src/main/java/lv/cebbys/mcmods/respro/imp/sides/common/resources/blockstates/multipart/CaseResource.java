package lv.cebbys.mcmods.respro.imp.sides.common.resources.blockstates.multipart;

import lv.cebbys.mcmods.respro.imp.loggers.ResproLogger;
import lv.cebbys.mcmods.respro.imp.resource.JsonObjectResource;
import lv.cebbys.mcmods.respro.imp.parser.JsonPart;
import lv.cebbys.mcmods.respro.imp.sides.common.resources.blockstates.variant.VariantResource;
import lv.cebbys.mcmods.respro.imp.component.BlockProperty;
import net.minecraft.resource.ResourceType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CaseResource extends JsonObjectResource {

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

    @Override
    protected boolean belongsTo(ResourceType type) {
        return ResourceType.CLIENT_RESOURCES.equals(type);
    }
}
