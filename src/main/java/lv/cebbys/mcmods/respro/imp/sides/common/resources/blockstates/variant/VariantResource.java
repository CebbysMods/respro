package lv.cebbys.mcmods.respro.imp.sides.common.resources.blockstates.variant;

import lv.cebbys.mcmods.respro.imp.loggers.ResproLogger;
import lv.cebbys.mcmods.respro.imp.resource.JsonObjectResource;
import lv.cebbys.mcmods.respro.imp.parser.JsonPart;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

public class VariantResource extends JsonObjectResource {
    @JsonPart(path = "model")
    protected String model;
    @JsonPart(path = "x")
    protected Integer x;
    @JsonPart(path = "y")
    protected Integer y;
    @JsonPart(path = "uvlock")
    protected Boolean uvlock;
    @JsonPart(path = "weight")
    protected Integer weight;

    public VariantResource model(String s) {
        if(model == null) {
            model = s;
        } else {
            ResproLogger.warn("Variant resource model has been already initialized. Tried to overwrite");
        }
        return this;
    }
    public VariantResource model(Identifier s) {
        return model(s.toString());
    }

    public VariantResource x(int s) {
        if(x == null) {
            x = s;
        } else {
            ResproLogger.warn("Variant resource x has been already initialized. Tried to overwrite");
        }
        return this;
    }

    public VariantResource y(int s) {
        if(y == null) {
            y = s;
        } else {
            ResproLogger.warn("Variant resource y has been already initialized. Tried to overwrite");
        }
        return this;
    }

    public VariantResource uvlock(boolean s) {
        if(uvlock == null) {
            uvlock = s;
        } else {
            ResproLogger.warn("Variant resource uvlock has been already initialized. Tried to overwrite");
        }
        return this;
    }

    public VariantResource weight(int s) {
        if(weight == null) {
            weight = s;
        } else {
            ResproLogger.warn("Variant resource weight has been already initialized. Tried to overwrite");
        }
        return this;
    }

    @Override
    protected boolean belongsTo(ResourceType type) {
        return ResourceType.CLIENT_RESOURCES.equals(type);
    }
}
