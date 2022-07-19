package lv.cebbys.mcmods.respro.component.resource.blockstates.variant;

import lv.cebbys.mcmods.respro.component.resource.JsonObjectResource;
import lv.cebbys.mcmods.respro.mapper.JsonPart;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

public class VariantResource extends JsonObjectResource {
    @JsonPart(value = "model")
    protected String model;
    @JsonPart(value = "x")
    protected Integer x;
    @JsonPart(value = "y")
    protected Integer y;
    @JsonPart(value = "uvlock")
    protected Boolean uvlock;
    @JsonPart(value = "weight")
    protected Integer weight;

    public VariantResource model(String s) {
        if (model == null) {
            model = s;
        } else {
//            ResproLogger.warn("Variant resource model has been already initialized. Tried to overwrite");
        }
        return this;
    }

    public VariantResource model(Identifier s) {
        return model(s.toString());
    }

    public VariantResource x(int s) {
        if (x == null) {
            x = s;
        } else {
//            ResproLogger.warn("Variant resource x has been already initialized. Tried to overwrite");
        }
        return this;
    }

    public VariantResource y(int s) {
        if (y == null) {
            y = s;
        } else {
//            ResproLogger.warn("Variant resource y has been already initialized. Tried to overwrite");
        }
        return this;
    }

    public VariantResource uvlock(boolean s) {
        if (uvlock == null) {
            uvlock = s;
        } else {
//            ResproLogger.warn("Variant resource uvlock has been already initialized. Tried to overwrite");
        }
        return this;
    }

    public VariantResource weight(int s) {
        if (weight == null) {
            weight = s;
        } else {
//            ResproLogger.warn("Variant resource weight has been already initialized. Tried to overwrite");
        }
        return this;
    }

    @Override
    protected boolean belongsTo(ResourceType type) {
        return ResourceType.CLIENT_RESOURCES.equals(type);
    }
}
